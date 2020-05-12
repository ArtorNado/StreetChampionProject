package com.example.streetchampionproject.api.scs

import com.example.streetchampionproject.api.scs.models.*
import com.example.streetchampionproject.login.data.models.UserAuth
import com.example.streetchampionproject.login.data.models.UserId
import com.example.streetchampionproject.registration.data.model.User
import io.reactivex.Single
import retrofit2.http.*

interface StreetChampionService {

    @POST("registration")
    fun registration(
        @Body user: User
    ):
            Single<StreetChampionResponse>

    @POST("signIn")
    fun logIn(
        @Body userAuth: UserAuth
    ):
            Single<AuthToken>


    @GET("user/{userId}")
    fun getUser(
        @Path("userId")  userId: Int
    ):
            Single<UserData>

    @GET("userId/{user-email}")
    fun getUserId(
        @Path("user-email")  email: String
    ):
            Single<UserId>

    @POST("getPlayers/{teamId}")
    fun getPlayers(
        @Path("teamId") teamId: Int
    ):
            Single<List<Players>>

    @POST("getTeams")
    fun getTeams(
    ):
            Single<List<Teams>>

    @POST("getTeamsByCity/{city}")
    fun getTeamsByCity(
        @Path("city") city: String
    ):
            Single<List<Teams>>

    @POST("getTeamsByName/{name}")
    fun getTeamsByName(
        @Path("name") name: String
    ):
            Single<List<Teams>>

    @POST("getTeam/{id}")
    fun getTeam(
        @Path("id") id: Int
    ):
            Single<Teams>

    @GET("determineStatus")
    fun getUserStatusInTeam(
        @Query("userId") userId: Int,
        @Query("teamId") teamId: Int
    ):
            Single<UserStatusInTeam>

    @POST("getNotificationByRecipient/{recipientId}")
    fun getNotificationByRecipientId(
        @Path("recipientId") recipientId: Int
    ):
            Single<List<Notification>>

    @GET("answerNotification")
    fun sendAnswerToNotif(
        @Query("notification") notificationId: Int,
        @Query("answer") answer: Int
    ):
            Single<StreetChampionResponse>

    @POST("sendNotification")
    fun sendNotification(
        @Body notification: NotificationForSend
    ):
            Single<StreetChampionResponse>

}
