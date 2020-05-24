package com.example.streetchampionproject.common.data.databse.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match_command")
data class MatchCommandEntity(
    @PrimaryKey
    val matchId: Int,
    val date: String,
    val time: String,
    val creatorId: Int,
    val firstTeamId: Int,
    val secondTeamId: Int,
    val matchCity: String,
    val description: String,
    val role: String,
    val firstTeamName: String,
    val secondTeamName: String
)
