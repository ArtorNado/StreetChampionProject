package com.example.streetchampionproject.clubPage.presentation.ui.overview.domain.interfaces

import io.reactivex.Completable
import io.reactivex.Observable

interface OverviewInteractor {

    fun getEndedCommandMatchLocal(matchType: String, teamId: Int): Observable<List<Any?>>

    fun updateEndedCommandMatch(matchType: String, teamId: Int): Completable


}
