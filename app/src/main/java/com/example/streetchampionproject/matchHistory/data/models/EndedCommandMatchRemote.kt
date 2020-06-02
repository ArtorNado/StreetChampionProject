package com.example.streetchampionproject.matchHistory.data.models

import com.example.streetchampionproject.clubs.data.models.TeamsRemote
import com.google.gson.annotations.SerializedName

data class EndedCommandMatchRemote(
    @SerializedName("matchId")
    val matchId: Int,

    @SerializedName("date")
    val date: String,

    @SerializedName("winTeamId")
    val winTeamId: Int,

    @SerializedName("goalsFirstTeam")
    val goalsFirstTeam: Int,

    @SerializedName("goalsSecondTeam")
    val goalsSecondTeam: Int,

    @SerializedName("firstTeam")
    val firstTeam: TeamsRemote,

    @SerializedName("secondTeam")
    val secondTeam: TeamsRemote

)
