package com.example.streetchampionproject.singleMatch.data.network

import com.example.streetchampionproject.clubPage.data.model.UserStatusInPlaceRemote
import com.example.streetchampionproject.singleMatch.data.models.MatchSingleRemote
import com.example.streetchampionproject.singleMatch.data.models.ParticipantRemote
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SingleMatchService {

    @POST("getSingleMatch/{id}")
    fun getSingleMatchById(
        @Path("id") matchId: Int
    ):
            Single<MatchSingleRemote>

    @GET("getUserStatusInMatch")
    fun getUserStatusInMatch(
        @Query("matchId") matchId: Int,
        @Query("userId") userId: Int
    ):
            Single<UserStatusInPlaceRemote>

    @GET("joinSingleMatch")
    fun joinSingleMatch(
        @Query("idSingleMatch") matchId: Int,
        @Query("participant") id: Int
    ):
            Completable

    @POST("endSingleMatch/{id}")
    fun endSingleMatch(
        @Path("id") id: Int
    ):
            Completable

    @POST("getParticipant/{id}")
    fun getParticipants(
        @Path("id") id: Int
    ):
            Single<List<ParticipantRemote>>
}
