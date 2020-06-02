package com.example.streetchampionproject.clubPage.data

import com.example.streetchampionproject.api.scs.models.NotificationForSend
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.api.scs.models.UserStatusInPlace
import com.example.streetchampionproject.clubPage.data.interfaces.ClubPageRepository
import com.example.streetchampionproject.clubPage.data.mappers.*
import com.example.streetchampionproject.clubPage.data.network.ClubPageService
import com.example.streetchampionproject.common.data.databse.dao.TeamsDao
import com.example.streetchampionproject.common.data.databse.dao.UserStatusInPlaceDao
import com.example.streetchampionproject.common.data.databse.models.TeamsEntity
import com.example.streetchampionproject.common.data.databse.models.UserStatusInPlaceEntity
import com.example.streetchampionproject.common.domain.Exceptions
import com.example.streetchampionproject.common.domain.ResponseCode
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class ClubPageRepositoryImpl @Inject constructor(
    private val clubPageService: ClubPageService,
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
        clubPageService.getTeam(id)
            .map { setUserLocal(mapTeamsRemoteToTeamsEntity(it)) }
            .onErrorResumeNext { error ->
                Single.error(onError(error))
            }
            .ignoreElement()

    private fun setUserLocal(teamsEntity: TeamsEntity) {
        teamsDao.setTeam(teamsEntity)
    }

    override fun getUserStatus(teamId: Int): Observable<UserStatusInPlace> =
        userStatusInTeamDao.getUserStatus(userId, teamId)
            .map { mapUserStatusEntityToUserStatusInTeam(it) }

    override fun updateUserStatus(teamId: Int): Completable =
        clubPageService.getUserStatusInTeam(userId, teamId)
            .map { setUserStatusLocal(mapUserStatusRemoteToUserStatusEntity(it, userId, teamId)) }
            .onErrorResumeNext { error ->
                Single.error(onError(error))
            }
            .ignoreElement()

    private fun setUserStatusLocal(userStatusInTeamEntity: UserStatusInPlaceEntity) {
        userStatusInTeamDao.setUserStatus(userStatusInTeamEntity)
    }

    override fun sendNotif(notification: NotificationForSend): Completable =
        clubPageService.sendNotification(
            mapNotificationFsToNotificationRemote(
                notification,
                userId
            )
        )
            .onErrorResumeNext { error ->
                when (error) {
                    is HttpException -> Completable.error(Exceptions.error(ResponseCode.JOIN_TEAM_ERROR))
                    else -> Completable.error(onError(error))
                }
            }

    private fun onError(error: Throwable) =
        when (error) {
            is UnknownHostException -> Exceptions.error(ResponseCode.INTERNET_ERROR)
            else -> Exceptions.error(ResponseCode.SERVER_ERROR)
        }
}
