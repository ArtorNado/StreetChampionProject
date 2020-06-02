package com.example.streetchampionproject.clubPage.presentation.ui.overview.data.network

import com.example.streetchampionproject.clubPage.presentation.ui.overview.data.models.EndedCommandMatchRemote
import com.example.streetchampionproject.match.data.models.MatchCommandRemote
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OverviewService {

    @GET("getEndedCommandMatches")
    fun getEndedCommandMatches(
        @Query("teamId") teamId: Int
    ):
            Single<List<EndedCommandMatchRemote>>

    @GET("getCommandMatches")
    fun getFeatureCommandMatches(
        @Query("teamId") teamId: Int
    ):
            Single<List<MatchCommandRemote>>
}
