package com.example.streetchampionproject.matches.data.models

import com.google.gson.annotations.SerializedName

data class MatchCommandRemote(
    @SerializedName("matchId")
    val matchId: Int,

    @SerializedName("date")
    val date: String,

    @SerializedName("time")
    val time: String,

    @SerializedName("creatorId")
    val creatorId: Int,

    @SerializedName("firstTeamId")
    val firstTeamId: Int,

    @SerializedName("firstTeamName")
    val firstTeamName: String,

    @SerializedName("secondTeamId")
    val secondTeamId: Int,

    @SerializedName("secondTeamName")
    val secondTeamName: String,

    @SerializedName("matchCity")
    val matchCity: String,

    @SerializedName("description")
    val description: String
)
