package com.example.streetchampionproject.clubPage.presentation.ui.squad.presentation.models

import com.google.gson.annotations.SerializedName

data class SquadRemote(
    @SerializedName("userId")
    var userId: Int,

    @SerializedName("userFirstName")
    var firstName: String,

    @SerializedName("userSecondName")
    var secondName: String,

    @SerializedName("userCity")
    var userCity: String
)
