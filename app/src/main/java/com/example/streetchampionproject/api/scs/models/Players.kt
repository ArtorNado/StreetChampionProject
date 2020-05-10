package com.example.streetchampionproject.api.scs.models

import com.google.gson.annotations.SerializedName

data class Players (
    @SerializedName("user_first_name")
    var fisrtName: String,

    @SerializedName("user_second_name")
    var secondName: String,

    @SerializedName("user_city")
    var userCity: String
)
