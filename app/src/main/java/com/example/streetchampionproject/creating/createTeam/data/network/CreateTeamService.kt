package com.example.streetchampionproject.creating.createTeam.data.network

import com.example.streetchampionproject.api.scs.models.CreateTeam
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateTeamService {

    @POST("createTeam")
    fun createTeam(
        @Body createTeam: CreateTeam
    ): Completable

}
