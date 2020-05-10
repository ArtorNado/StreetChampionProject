package com.example.streetchampionproject.api.scs.models

import com.google.gson.annotations.SerializedName

data class AuthToken(
    @SerializedName("token")
    var token: String
)
