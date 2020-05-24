package com.example.streetchampionproject.singleMatch.data.models

import com.example.streetchampionproject.api.scs.models.Teams
import com.google.gson.annotations.SerializedName

data class ParticipantRemote(
    @SerializedName("userId")
    var userId: Int,

    @SerializedName("userFirstName")
    var userFirstName: String,

    @SerializedName("userSecondName")
    var userSecondName: String,

    @SerializedName("userGender")
    var userGender: String,

    @SerializedName("userCity")
    var userCity: String,

    @SerializedName("team")
    var team: Teams?
)
