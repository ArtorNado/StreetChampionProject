package com.example.streetchampionproject.api.scs.models

data class CreateCommandMatch(
    val date: String,
    val time: String,
    val creatorId: Int,
    val matchCity: String,
    val description: String
)
