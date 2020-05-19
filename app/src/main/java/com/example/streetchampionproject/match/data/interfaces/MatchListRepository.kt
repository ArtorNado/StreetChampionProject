package com.example.streetchampionproject.match.data.interfaces

import com.example.streetchampionproject.api.scs.models.MatchSingle
import io.reactivex.Completable
import io.reactivex.Observable

interface MatchListRepository {

    fun updateSingleMatchListWithRole(role: String): Completable

    fun getSingleMatchLocal(role: String): Observable<List<MatchSingle>>

    fun updateSingleMatchWithoutRole(): Completable

}
