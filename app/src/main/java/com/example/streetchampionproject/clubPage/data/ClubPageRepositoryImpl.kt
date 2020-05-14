package com.example.streetchampionproject.clubPage.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.models.NotificationForSend
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.api.scs.models.UserStatusInTeam
import com.example.streetchampionproject.clubPage.data.interfaces.ClubPageRepository
import com.example.streetchampionproject.clubPage.data.mappers.*
import com.example.streetchampionproject.common.data.databse.dao.TeamsDao
import com.example.streetchampionproject.common.data.databse.dao.UserStatusInTeamDao
import com.example.streetchampionproject.common.data.databse.models.TeamsEntity
import com.example.streetchampionproject.common.data.databse.models.UserStatusInTeamEntity
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ClubPageRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService,
    private val teamsDao: TeamsDao,
    private val userStatusInTeamDao: UserStatusInTeamDao,
    localStorage: LocalStorage
) : ClubPageRepository {

    private val userId = localStorage.readMessage("userId")?.toInt() ?: 0

    override fun getTeamLocal(id: Int): Observable<Teams> =
        teamsDao.getTeam(id)
            .map {
                mapTeamsEntityToTeams(
                    it
                )
            }

    override fun updateTeam(id: Int): Completable =
        streetChampionService.getTeam(id)
            .map { mapTeamsRemoteToTeamsEntity(it) }
            .doOnSuccess { setUserLocal(it) }
            .ignoreElement()

    private fun setUserLocal(teamsEntity: TeamsEntity) {
        teamsDao.setTeam(teamsEntity)
    }

    override fun getUserStatus(teamId: Int): Observable<UserStatusInTeam> =
        userStatusInTeamDao.getUserStatus(userId, teamId)
            .map { mapUserStatusEntityToUserStatusInTeam(it) }

    override fun updateUserStatus(teamId: Int): Completable =
        streetChampionService.getUserStatusInTeam(userId, teamId)
            .map { mapUserStatusRemoteToUserStatusEntity(it, userId, teamId) }
            .doOnSuccess { setUserStatusLocal(it) }
            .ignoreElement()

    private fun setUserStatusLocal(userStatusInTeamEntity: UserStatusInTeamEntity) {
        userStatusInTeamDao.setUserStatus(userStatusInTeamEntity)
    }

    override fun sendNotif(notification: NotificationForSend): Completable =
        streetChampionService.sendNotification(
            mapNotificationFsToNotificationRemote(
                notification,
                userId
            )
        )
}
