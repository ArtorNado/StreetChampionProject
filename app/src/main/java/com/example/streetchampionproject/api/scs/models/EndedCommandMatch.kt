package com.example.streetchampionproject.api.scs.models

data class EndedCommandMatch(
    val matchId: Int,
    val date: String,
    val winTeamId: Int,
    val firstTeamName: String,
    val goalsFirstTeam: Int,
    val secondTeamName: String,
    val goalsSecondTeam: Int
)
