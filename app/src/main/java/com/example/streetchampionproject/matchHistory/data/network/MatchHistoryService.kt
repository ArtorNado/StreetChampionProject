package com.example.streetchampionproject.matchHistory.data.network

import com.example.streetchampionproject.matchHistory.data.models.EndedCommandMatchRemote
import com.example.streetchampionproject.matches.data.models.MatchCommandRemote
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MatchHistoryService {

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
