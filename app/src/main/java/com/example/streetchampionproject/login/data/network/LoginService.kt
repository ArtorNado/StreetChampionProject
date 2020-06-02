package com.example.streetchampionproject.login.data.network

import com.example.streetchampionproject.api.scs.models.AuthToken
import com.example.streetchampionproject.login.data.models.UserAuth
import com.example.streetchampionproject.login.data.models.UserId
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginService {

    @POST("signIn")
    fun logIn(
        @Body userAuth: UserAuth
    ):
            Single<AuthToken>

    @GET("userId/{user-email}")
    fun getUserId(
        @Path("user-email") email: String
    ):
            Single<UserId>
}
