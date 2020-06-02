package com.example.streetchampionproject.profile.data.network

import com.example.streetchampionproject.profile.data.model.UserDataRemote
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileService {

    @GET("user/{userId}")
    fun getUser(
        @Path("userId") userId: Int
    ):
            Single<UserDataRemote>

}
