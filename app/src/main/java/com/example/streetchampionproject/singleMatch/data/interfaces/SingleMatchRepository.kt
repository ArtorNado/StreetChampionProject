package com.example.streetchampionproject.singleMatch.data.interfaces

import com.example.streetchampionproject.api.scs.models.MatchSingleDetailInfo
import com.example.streetchampionproject.api.scs.models.UserStatusInPlace
import com.example.streetchampionproject.common.data.databse.models.MatchSingleEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface SingleMatchRepository {

    fun getSingleMatchInfoLocal(matchId: Int): Observable<MatchSingleDetailInfo>

    fun updateSingleMatchInfo(matchId: Int): Completable

    fun getUserStatusInMatchLocal(matchId: Int): Observable<UserStatusInPlace>

    fun updateUserStatusInMatch(matchId: Int): Completable

    fun joinInMatch(matchId: Int): Completable

    fun endSingleMatch(matchId: Int): Completable

    fun setSingleMatchLocal(matchSingleEntity: MatchSingleEntity)

    fun updateParticipants(matchId: Int): Single<Unit>
}
