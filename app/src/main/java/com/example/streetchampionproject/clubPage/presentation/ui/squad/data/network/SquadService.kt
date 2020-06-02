package com.example.streetchampionproject.clubPage.presentation.ui.squad.data.network

import com.example.streetchampionproject.clubPage.presentation.ui.squad.data.models.SquadRemote
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
