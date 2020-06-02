package com.example.streetchampionproject.singleMatch.data

import com.example.streetchampionproject.api.scs.models.MatchSingleDetailInfo
import com.example.streetchampionproject.api.scs.models.UserStatusInPlace
import com.example.streetchampionproject.common.data.databse.dao.MatchSingleDao
import com.example.streetchampionproject.common.data.databse.dao.ParticipantsDao
import com.example.streetchampionproject.common.data.databse.dao.UserStatusInPlaceDao
import com.example.streetchampionproject.common.data.databse.models.MatchSingleEntity
import com.example.streetchampionproject.common.data.databse.models.ParticipantsEntity
import com.example.streetchampionproject.common.data.databse.models.UserStatusInPlaceEntity
import com.example.streetchampionproject.common.domain.Exceptions
import com.example.streetchampionproject.common.domain.ResponseCode
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import com.example.streetchampionproject.singleMatch.data.interfaces.SingleMatchRepository
import com.example.streetchampionproject.singleMatch.data.mappers.*
import com.example.streetchampionproject.singleMatch.data.network.SingleMatchService
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.net.UnknownHostException
import javax.inject.Inject

class SingleMatchRepositoryImpl @Inject constructor(
    private val singleMatchService: SingleMatchService,
    private val matchSingleDao: MatchSingleDao,
    private val userStatusInPlaceDao: UserStatusInPlaceDao,
    private val localStorage: LocalStorage,
    private val participantsDao: ParticipantsDao
) : SingleMatchRepository {

    val userId = localStorage.readMessage("userId")?.toInt() ?: 0

    override fun getSingleMatchInfoLocal(matchId: Int): Observable<MatchSingleDetailInfo> =
        matchSingleDao.getSingleMatchById(matchId)
            .map { mapMatchEntityToMatchSingle(it) }

    override fun updateSingleMatchInfo(matchId: Int): Completable =
        singleMatchService.getSingleMatchById(matchId)
            .map { setSingleMatchLocal(mapMatchSingleRemoteToMatchSingleEntity(it)) }
            .ignoreElement()
            .onErrorResumeNext { Completable.error(onError(it)) }

    override fun setSingleMatchLocal(matchSingleEntity: MatchSingleEntity) =
        matchSingleDao.setSingleMatch(matchSingleEntity)


    override fun getUserStatusInMatchLocal(
        matchId: Int
    ): Observable<UserStatusInPlace> =
        userStatusInPlaceDao.getUserStatus(userId, matchId)
            .map { mapUserStatusEntityToUserStatusInPlace(it) }

    override fun updateUserStatusInMatch(matchId: Int): Completable =
        singleMatchService.getUserStatusInMatch(matchId, userId)
            .map { setUserStatusLocal(mapUserStatusRemoteToUserStatusEntity(it, userId, matchId)) }
            .ignoreElement()
            .onErrorResumeNext { Completable.error(onError(it)) }

    override fun joinInMatch(matchId: Int): Completable =
        singleMatchService.joinSingleMatch(matchId, userId)
            .onErrorResumeNext { Completable.error(onError(it)) }

    override fun endSingleMatch(matchId: Int): Completable =
        singleMatchService.endSingleMatch(matchId)
            .onErrorResumeNext { Completable.error(onError(it)) }

    private fun setUserStatusLocal(userStatusInPlaceEntity: UserStatusInPlaceEntity) {
        userStatusInPlaceDao.setUserStatus(userStatusInPlaceEntity)
    }

    private fun onError(error: Throwable): Exceptions =
        when (error) {
            is UnknownHostException -> Exceptions.error(ResponseCode.INTERNET_ERROR)
            else -> Exceptions.error(ResponseCode.SERVER_ERROR)
        }

    override fun updateParticipants(matchId: Int): Single<Unit> {
        return singleMatchService.getParticipants(matchId)
            .map {
                setParticipantsLocal(mapParticipantsRemoteToPartEntity(it, matchId))
            }
    }

    private fun setParticipantsLocal(participantsEntity: List<ParticipantsEntity>) {
        participantsDao.setParticipants(participantsEntity)
    }


}
