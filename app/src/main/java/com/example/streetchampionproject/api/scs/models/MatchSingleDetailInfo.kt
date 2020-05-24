package com.example.streetchampionproject.api.scs.models

data class MatchSingleDetailInfo(
    val matchId: Int,
    val date: String,
    val time: String,
    val creatorId: Int,
    var numberParticipant: Int,
    val currentNumberParticipant: Int,
    val description: String?,
    val matchCity: String?
)
