package com.example.streetchampionproject.match.domain.interfaces

import com.example.streetchampionproject.api.scs.models.MatchSingle
import io.reactivex.Completable
import io.reactivex.Observable

interface MatchListInteractor {

    fun getMatchList(matchType: String, role: String): Observable<List<MatchSingle>>

    fun updateMatchList(matchType: String, role: String): Completable


}
