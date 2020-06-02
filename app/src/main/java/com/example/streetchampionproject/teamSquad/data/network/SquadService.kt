package com.example.streetchampionproject.teamSquad.data.network

import com.example.streetchampionproject.teamSquad.data.models.SquadRemote
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Path

interface SquadService {

    @POST("getPlayers/{teamId}")
    fun getPlayers(
        @Path("teamId") teamId: Int
    ):
            Single<List<SquadRemote>>


}
