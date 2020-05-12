package com.example.streetchampionproject.api.scs.models

import com.google.gson.annotations.SerializedName

data class Players (
    @SerializedName("userFirstName")
    var fisrtName: String,

    @SerializedName("userSecondName")
    var secondName: String,

    @SerializedName("userCity")
    var userCity: String
)
