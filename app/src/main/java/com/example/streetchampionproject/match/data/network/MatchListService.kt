package com.example.streetchampionproject.match.data.network

import com.example.streetchampionproject.match.data.models.MatchCommandRemote
import com.example.streetchampionproject.match.data.models.MatchSingleRemote
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MatchListService {

    @GET("getSingleMatchByRole")
    fun getSingleMatchByRole(
        @Query("userId") userId: Int,
        @Query("role") role: String
    ):
            Single<List<MatchSingleRemote>>

    @POST("getAllSingleMatch")
    fun getMatchSingle(): Single<List<MatchSingleRemote>>

    @GET("getCommandMatchByRole")
    fun getCommandMatchByRole(
        @Query("userId") userId: Int,
        @Query("role") role: String
    ):
            Single<List<MatchCommandRemote>>

    @POST("getAllCommandMatch")
    fun getCommandMatch(): Single<List<MatchCommandRemote>>

    @GET("getAllCommandMatchByCity")
    fun getCommandMatchByCity(
        @Query("city") city: String
    ):
            Single<List<MatchCommandRemote>>

    @GET("getCommandMatchByRoleAndCity")
    fun getCommandMatchByRoleAndCity(
        @Query("userId") userId: Int,
        @Query("role") role: String,
        @Query("city") city: String
    ):
            Single<List<MatchCommandRemote>>

    @GET("getSingleMatchByCity")
    fun getSingleMatchByCity(
        @Query("city") city: String
    ):
            Single<List<MatchSingleRemote>>

    @GET("getSingleMatchByRoleAndCity")
    fun getSingleMatchByRoleAndCity(
        @Query("userId") userId: Int,
        @Query("role") role: String,
        @Query("city") city: String
    ):
            Single<List<MatchSingleRemote>>
}
