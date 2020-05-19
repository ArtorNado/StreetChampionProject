package com.example.streetchampionproject.api.scs

import com.example.streetchampionproject.api.scs.models.*
import com.example.streetchampionproject.clubPage.data.model.NotificationRemote
import com.example.streetchampionproject.clubPage.data.model.TeamRemote
import com.example.streetchampionproject.clubPage.data.model.UserStatusInPlaceRemote
import com.example.streetchampionproject.clubPage.presentation.ui.squad.data.models.SquadRemote
import com.example.streetchampionproject.login.data.models.UserAuth
import com.example.streetchampionproject.login.data.models.UserId
import com.example.streetchampionproject.main.presentation.ui.clubs.data.models.TeamsRemote
import com.example.streetchampionproject.main.presentation.ui.profile.data.model.UserDataRemote
import com.example.streetchampionproject.match.data.models.MatchSingleRemote
import com.example.streetchampionproject.registration.data.model.User
import com.example.streetchampionproject.singleMatch.data.models.ParticipantRemote
import io.reactivex.Completable
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
        @Path("userId") userId: Int
    ):
            Single<UserDataRemote>

    @GET("userId/{user-email}")
    fun getUserId(
        @Path("user-email") email: String
    ):
            Single<UserId>

    @POST("getPlayers/{teamId}")
    fun getPlayers(
        @Path("teamId") teamId: Int
    ):
            Single<List<SquadRemote>>

    @POST("getTeams")
    fun getTeams(
    ):
            Single<List<TeamsRemote>>

    @POST("getTeamsByCity/{city}")
    fun getTeamsByCity(
        @Path("city") city: String
    ):
            Single<List<TeamsRemote>>

    @POST("getTeamsByName/{name}")
    fun getTeamsByName(
        @Path("name") name: String
    ):
            Single<List<Teams>>

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
        @Body notification: NotificationRemote
    ): Completable

    @POST("createTeam")
    fun createTeam(
        @Body createTeam: CreateTeam
    ): Completable

    @POST("getAllSingleMatch")
    fun getMatchSingle(): Single<List<MatchSingleRemote>>

    @GET("getSingleMatchByRole")
    fun getSingleMatchByRole(
        @Query("userId") userId: Int,
        @Query("role") role: String
    ):
            Single<List<MatchSingleRemote>>

    @POST("getSingleMatch/{id}")
    fun getSingleMatchById(
        @Path("id") matchId: Int
    ):
            Single<com.example.streetchampionproject.singleMatch.data.models.MatchSingleRemote>

    @GET("getUserStatusInMatch")
    fun getUserStatusInMatch(
        @Query("matchId") matchId: Int,
        @Query("userId") userId: Int
    ):
            Single<UserStatusInPlaceRemote>

    @POST("getParticipant/{id}")
    fun getParticipants(
        @Path("id") id: Int
    ):
            Single<List<ParticipantRemote>>

    @GET("joinSingleMatch")
    fun joinSingleMatch(
        @Query("idSingleMatch") matchId: Int,
        @Query("participant") id: Int
    ):
            Completable
}
