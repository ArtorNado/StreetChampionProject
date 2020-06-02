package com.example.streetchampionproject.creating.createMatch.data.network

import com.example.streetchampionproject.api.scs.models.CreateCommandMatch
import com.example.streetchampionproject.api.scs.models.CreateSingleMatch
import com.example.streetchampionproject.api.scs.models.UserTeamRole
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface CreateMatchService {

    @POST("createCommandMatch")
    fun createCommandMatch(
        @Body createCommandMatch: CreateCommandMatch
    ): Completable

    @POST("createMatch")
    fun createSingleMatch(
        @Body createSingleMatch: CreateSingleMatch
    ): Completable

    @POST("determineUserStatus/{userId}")
    fun determineUserStatus(
        @Path("userId") userId: Int
    )
            : Single<UserTeamRole>
}
