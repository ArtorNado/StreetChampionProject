package com.example.streetchampionproject.clubPage.data

import android.util.Log
import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.models.NotificationForSend
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.api.scs.models.UserStatusInPlace
import com.example.streetchampionproject.clubPage.data.interfaces.ClubPageRepository
import com.example.streetchampionproject.clubPage.data.mappers.*
import com.example.streetchampionproject.common.data.databse.dao.TeamsDao
import com.example.streetchampionproject.common.data.databse.dao.UserStatusInPlaceDao
import com.example.streetchampionproject.common.data.databse.models.TeamsEntity
import com.example.streetchampionproject.common.data.databse.models.UserStatusInPlaceEntity
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ClubPageRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService,
    private val teamsDao: TeamsDao,
    private val userStatusInTeamDao: UserStatusInPlaceDao,
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
            .map { setUserLocal(mapTeamsRemoteToTeamsEntity(it)) }
            .ignoreElement()

    private fun setUserLocal(teamsEntity: TeamsEntity) {
        Log.e("SET_USER_LOCAL", "SET")
        teamsDao.setTeam(teamsEntity)
    }

    override fun getUserStatus(teamId: Int): Observable<UserStatusInPlace> =
        userStatusInTeamDao.getUserStatus(userId, teamId)
            .map { mapUserStatusEntityToUserStatusInTeam(it) }

    override fun updateUserStatus(teamId: Int): Completable =
        streetChampionService.getUserStatusInTeam(userId, teamId)
            .map { setUserStatusLocal(mapUserStatusRemoteToUserStatusEntity(it, userId, teamId)) }
            .ignoreElement()

    private fun setUserStatusLocal(userStatusInTeamEntity: UserStatusInPlaceEntity) {
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
