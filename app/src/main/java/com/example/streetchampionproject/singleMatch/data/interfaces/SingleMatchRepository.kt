package com.example.streetchampionproject.singleMatch.data.interfaces

import com.example.streetchampionproject.api.scs.models.MatchSingleDetailInfo
import com.example.streetchampionproject.api.scs.models.UserStatusInPlace
import io.reactivex.Completable
import io.reactivex.Observable

interface SingleMatchRepository {

    fun getSingleMatchInfoLocal(matchId: Int): Observable<MatchSingleDetailInfo>

    fun updateSingleMatchInfo(matchId: Int): Completable

    fun getUserStatusInMatchLocal(matchId: Int): Observable<UserStatusInPlace>

    fun updateUserStatusInMatch(matchId: Int): Completable

    fun joinInMatch(matchId: Int): Completable

}
