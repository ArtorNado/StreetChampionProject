package com.example.streetchampionproject.match.data.models

data class MatchCommandRemote(
    val matchId: Int,
    val date: String,
    val time: String,
    val creatorId: Int,
    val firstTeamId: Int,
    val secondTeamId: Int,
    val matchCity: String,
    val description: String
)
