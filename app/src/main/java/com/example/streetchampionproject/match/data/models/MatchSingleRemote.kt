package com.example.streetchampionproject.match.data.models

import com.google.gson.annotations.SerializedName

data class MatchSingleRemote(
    @SerializedName("matchId")
    val matchId: Int,

    @SerializedName("date")
    val date: String,

    @SerializedName("time")
    val time: String,

    @SerializedName("creatorId")
    val creatorId: Int,

    @SerializedName("numberParticipant")
    val numberParticipant: Int,

    @SerializedName("currentNumberParticipant")
    val currentNumberParticipant: Int,

    @SerializedName("description")
    val description: String?,

    @SerializedName("matchCity")
    val matchCity: String?
)
