package com.example.streetchampionproject.api.scs.models

data class MatchCommand(
    val matchId: Int,
    val date: String,
    val time: String,
    val creatorId: Int,
    val firstTeamId: Int,
    val secondTeamId: Int,
    val matchCity: String,
    val description: String
)
