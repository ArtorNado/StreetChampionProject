package com.example.streetchampionproject.singleMatch.data.network

import com.example.streetchampionproject.singleMatch.data.models.ParticipantRemote
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Path

interface ParticipantService {

    @POST("getParticipant/{id}")
    fun getParticipants(
        @Path("id") id: Int
    ):
            Single<List<ParticipantRemote>>

}
