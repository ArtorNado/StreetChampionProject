package com.example.streetchampionproject.common.data.databse.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match_single")
data class MatchSingleEntity(
    @PrimaryKey
    val matchId: Int,
    val date: String,
    val time: String,
    val creatorId: Int,
    val numberParticipant: Int,
    val currentNumberParticipant: Int,
    val description: String,
    val matchCity: String,
    var role : String?
)
