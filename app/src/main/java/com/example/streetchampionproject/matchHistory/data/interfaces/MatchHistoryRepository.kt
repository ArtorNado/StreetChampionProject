package com.example.streetchampionproject.matchHistory.data.interfaces

import io.reactivex.Completable
import io.reactivex.Observable

interface MatchHistoryRepository {

    fun getEndedMatchListLocal(teamId: Int): Observable<List<Any?>>

    fun updateEndedMatchList(teamId: Int): Completable

    fun getFeatureMatchListLocal(teamId: Int): Observable<List<Any?>>

    fun updateFeatureMatchList(teamId: Int): Completable
}
