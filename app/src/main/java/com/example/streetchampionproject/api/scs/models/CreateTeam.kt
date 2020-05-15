package com.example.streetchampionproject.api.scs.models

data class CreateTeam(
    val teamName: String,
    val teamCity: String,
    val creatorId: Int
)
