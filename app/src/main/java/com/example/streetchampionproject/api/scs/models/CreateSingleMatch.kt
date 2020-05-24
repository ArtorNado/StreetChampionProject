package com.example.streetchampionproject.api.scs.models

data class CreateSingleMatch(
    val date: String,
    val time: String,
    var creatorId: Int,
    val numberParticipant: Int,
    val description: String,
    val matchCity: String
)
