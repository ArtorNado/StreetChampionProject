package com.example.streetchampionproject.api.scs

import com.example.streetchampionproject.api.scs.response.AuthToken
import com.example.streetchampionproject.api.scs.response.Players
import com.example.streetchampionproject.api.scs.response.StreetChampionResponse
import com.example.streetchampionproject.api.scs.response.Teams
import com.example.streetchampionproject.login.data.models.UserAuth
import com.example.streetchampionproject.login.data.models.UserId
import com.example.streetchampionproject.main.presentation.ui.profile.data.model.UserData
import com.example.streetchampionproject.registration.data.model.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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


    @GET("/user/{userId}")
    fun getUser(
        @Path("userId")  userId: String
    ):
            Single<UserData>

    @GET("/userId/{user-email}")
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



}
