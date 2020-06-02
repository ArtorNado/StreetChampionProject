package com.example.streetchampionproject.clubPage.data.network

import com.example.streetchampionproject.clubPage.data.model.NotificationRemote
import com.example.streetchampionproject.clubPage.data.model.TeamRemote
import com.example.streetchampionproject.clubPage.data.model.UserStatusInPlaceRemote
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface ClubPageService {

    @POST("getTeam/{id}")
    fun getTeam(
        @Path("id") id: Int
    ):
            Single<TeamRemote>

    @GET("determineStatus")
    fun getUserStatusInTeam(
        @Query("userId") userId: Int,
        @Query("teamId") teamId: Int
    ):
            Single<UserStatusInPlaceRemote>

    @POST("sendNotification")
    fun sendNotification(
        @Body notification: NotificationRemote
    ): Completable
}
