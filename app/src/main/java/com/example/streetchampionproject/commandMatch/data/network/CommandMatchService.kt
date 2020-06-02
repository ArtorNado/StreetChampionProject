package com.example.streetchampionproject.commandMatch.data.network

import com.example.streetchampionproject.api.scs.models.UserTeamRole
import com.example.streetchampionproject.match.data.models.MatchCommandRemote
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CommandMatchService {

    @POST("getCommandMatch/{id}")
    fun getCommandMatch(
        @Path("id") id: Int
    ):
            Single<MatchCommandRemote>

    @POST("determineUserStatus/{userId}")
    fun determineUserStatus(
        @Path("userId") userId: Int
    )
            : Single<UserTeamRole>

    @GET("endCommandMatch")
    fun endCommandMatch(
        @Query("matchId") matchId: Int,
        @Query("firstTeamScore") firstTeamScore: Int,
        @Query("secondTeamsScore") secondTeamScore: Int
    ):
            Completable

    @POST("joinCommandMatch")
    fun joinCommandMatch(
        @Query("idCommandMatch") matchId: Int,
        @Query("recipient") id: Int
    ): Completable
}
