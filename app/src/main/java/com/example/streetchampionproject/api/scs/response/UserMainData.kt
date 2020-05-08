package com.example.streetchampionproject.api.scs.response

import com.google.gson.annotations.SerializedName

data class UserMainData(
    @SerializedName("userId")
    var userId: Int,

    @SerializedName("userFirstName")
    var userFirstName: String,

    @SerializedName("userSecondName")
    var userSecondName: String
)
