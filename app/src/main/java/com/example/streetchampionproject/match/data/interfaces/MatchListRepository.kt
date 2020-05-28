package com.example.streetchampionproject.match.data.interfaces

import io.reactivex.Single

interface MatchListRepository {

    fun updateSingleMatchListWithRole(role: String): Single<List<Any?>>

    fun getSingleMatchLocal(role: String): Single<List<Any?>>

    fun updateSingleMatchWithoutRole(): Single<List<Any?>>

    fun getCommandMatchLocal(role: String): Single<List<Any?>>

    fun updateCommandMatchWithRole(role: String): Single<List<Any?>>

    fun updateCommandMatchWithoutRole(): Single<List<Any?>>

    fun getCommandMatchByCityLocal(city: String): Single<List<Any?>>

    fun getCommandMatchByRoleAndCityLocal(role: String, city: String): Single<List<Any?>>

    fun updateCommandMatchByCity(city: String): Single<List<Any?>>

    fun updateCommandMatchByRoleAndCity(role: String, city: String): Single<List<Any?>>

    fun getSingleMatchByCity(city: String): Single<List<Any?>>

    fun getSingleMatchByRoleAndCity(role: String, city: String): Single<List<Any?>>

    fun updateSingleMatchByCity(city: String): Single<List<Any?>>

    fun updateSingleMatchByRoleAndCity(role: String, city: String): Single<List<Any?>>

}
