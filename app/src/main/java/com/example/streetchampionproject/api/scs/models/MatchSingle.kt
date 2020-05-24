package com.example.streetchampionproject.api.scs.models

data class MatchSingle(
    val matchId: Int,
    val date: String,
    val numberParticipant: Int,
    val currentNumberParticipant: Int,
    val matchCity: String
)
