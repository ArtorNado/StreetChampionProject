package com.example.streetchampionproject.api.scs.response

import com.google.gson.annotations.SerializedName

data class AuthToken(
    @SerializedName("token")
    var token: String
)
