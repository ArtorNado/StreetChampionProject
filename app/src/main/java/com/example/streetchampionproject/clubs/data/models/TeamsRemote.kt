package com.example.streetchampionproject.clubs.data.models

import com.google.gson.annotations.SerializedName

data class TeamsRemote(
    @SerializedName("teamId")
    var teamId: Int,

    @SerializedName("teamName")
    var teamName: String,

    @SerializedName("teamCity")
    var teamCity: String,

    @SerializedName("creatorId")
    var creatorId: Int
)
