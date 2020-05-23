package com.example.streetchampionproject.match.data.interfaces

import io.reactivex.Single

interface MatchListRepository {

    fun updateSingleMatchListWithRole(role: String): Single<List<Any?>>

    fun getSingleMatchLocal(role: String): Single<List<Any?>>

    fun updateSingleMatchWithoutRole(): Single<List<Any?>>

    fun getCommandMatchLocal(role: String): Single<List<Any?>>

    fun updateCommandMatchWithRole(role: String): Single<List<Any?>>

    fun updateCommandMatchWithoutRole(): Single<List<Any?>>

}
