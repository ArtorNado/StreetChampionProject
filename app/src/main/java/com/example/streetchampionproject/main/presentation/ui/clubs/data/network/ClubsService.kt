package com.example.streetchampionproject.main.presentation.ui.clubs.data.network

import com.example.streetchampionproject.main.presentation.ui.clubs.data.models.TeamsRemote
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Path

interface ClubsService {

    @POST("getTeams")
    fun getTeams(
    ):
            Single<List<TeamsRemote>>

    @POST("getTeamsByCity/{city}")
    fun getTeamsByCity(
        @Path("city") city: String
    ):
            Single<List<TeamsRemote>>
}
