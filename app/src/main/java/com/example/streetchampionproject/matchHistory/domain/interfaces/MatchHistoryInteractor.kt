package com.example.streetchampionproject.matchHistory.domain.interfaces

import io.reactivex.Completable
import io.reactivex.Observable

interface MatchHistoryInteractor {

    fun getEndedCommandMatchLocal(matchType: String, teamId: Int): Observable<List<Any?>>

    fun updateEndedCommandMatch(matchType: String, teamId: Int): Completable


}
