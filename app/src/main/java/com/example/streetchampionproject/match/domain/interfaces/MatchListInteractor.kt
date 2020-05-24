package com.example.streetchampionproject.match.domain.interfaces

import io.reactivex.Single

interface MatchListInteractor {

    fun getMatchList(matchType: String, role: String): Single<List<Any?>>

    fun updateMatchList(matchType: String, role: String): Single<List<Any?>>

}
