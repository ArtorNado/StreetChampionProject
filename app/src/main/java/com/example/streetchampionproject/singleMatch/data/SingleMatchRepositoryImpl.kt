package com.example.streetchampionproject.singleMatch.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.models.MatchSingleDetailInfo
import com.example.streetchampionproject.api.scs.models.UserStatusInPlace
import com.example.streetchampionproject.common.data.databse.dao.MatchSingleDao
import com.example.streetchampionproject.common.data.databse.dao.UserStatusInPlaceDao
import com.example.streetchampionproject.common.data.databse.models.MatchSingleEntity
import com.example.streetchampionproject.common.data.databse.models.UserStatusInPlaceEntity
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import com.example.streetchampionproject.singleMatch.data.interfaces.SingleMatchRepository
import com.example.streetchampionproject.singleMatch.data.mappers.mapMatchEntityToMatchSingle
import com.example.streetchampionproject.singleMatch.data.mappers.mapMatchSingleRemoteToMatchSingleEntity
import com.example.streetchampionproject.singleMatch.data.mappers.mapUserStatusEntityToUserStatusInPlace
import com.example.streetchampionproject.singleMatch.data.mappers.mapUserStatusRemoteToUserStatusEntity
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class SingleMatchRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService,
    private val matchSingleDao: MatchSingleDao,
    private val userStatusInPlaceDao: UserStatusInPlaceDao,
    private val localStorage: LocalStorage
) : SingleMatchRepository {

    val userId = localStorage.readMessage("userId")?.toInt() ?: 0

    override fun getSingleMatchInfoLocal(matchId: Int): Observable<MatchSingleDetailInfo> =
        matchSingleDao.getSingleMatchById(matchId)
            .map { mapMatchEntityToMatchSingle(it) }

    override fun updateSingleMatchInfo(matchId: Int): Completable =
        streetChampionService.getSingleMatchById(matchId)
            .map { updateSingleMatchLocal(mapMatchSingleRemoteToMatchSingleEntity(it)) }
            .ignoreElement()

    private fun updateSingleMatchLocal(matchSingleEntity: MatchSingleEntity) {
        matchSingleDao.setSingleMatch(matchSingleEntity)
    }

    override fun getUserStatusInMatchLocal(
        matchId: Int
    ): Observable<UserStatusInPlace> =
        userStatusInPlaceDao.getUserStatus(userId, matchId)
            .map { mapUserStatusEntityToUserStatusInPlace(it) }

    override fun updateUserStatusInMatch(matchId: Int): Completable =
        streetChampionService.getUserStatusInMatch(matchId, userId)
            .map { setUserStatusLocal(mapUserStatusRemoteToUserStatusEntity(it, userId, matchId)) }
            .ignoreElement()

    override fun joinInMatch(matchId: Int): Completable =
        streetChampionService.joinSingleMatch(matchId, userId)


    private fun setUserStatusLocal(userStatusInPlaceEntity: UserStatusInPlaceEntity) {
        userStatusInPlaceDao.setUserStatus(userStatusInPlaceEntity)
    }


}
