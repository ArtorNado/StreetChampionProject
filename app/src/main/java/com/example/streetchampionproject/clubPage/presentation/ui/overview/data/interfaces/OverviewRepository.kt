package com.example.streetchampionproject.clubPage.presentation.ui.overview.data.interfaces

import io.reactivex.Completable
import io.reactivex.Observable

interface OverviewRepository {

    fun getEndedMatchListLocal(teamId: Int): Observable<List<Any?>>

    fun updateEndedMatchList(teamId: Int): Completable

    fun getFeatureMatchListLocal(teamId: Int): Observable<List<Any?>>

    fun updateFeatureMatchList(teamId: Int): Completable
}
