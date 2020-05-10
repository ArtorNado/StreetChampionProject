package com.example.streetchampionproject.api.scs.models

import com.google.gson.annotations.SerializedName

data class Teams (

    @SerializedName("teamId")
    var teamId: Int,

    @SerializedName("teamName")
    var teamName: String,

    @SerializedName("teamCity")
    var teamCity: String,

    @SerializedName("creatorId")
    var creatorId: Int
)
