package com.example.streetchampionproject.matches.domain.interfaces

import io.reactivex.Single

interface MatchListInteractor {

    fun getMatchList(matchType: String, role: String, city: String): Single<List<Any?>>

    fun updateMatchList(matchType: String, role: String, city: String): Single<List<Any?>>

}
