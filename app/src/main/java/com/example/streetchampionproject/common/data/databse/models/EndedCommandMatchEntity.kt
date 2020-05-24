package com.example.streetchampionproject.common.data.databse.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ended_command_match")
data class EndedCommandMatchEntity(
    @PrimaryKey
    val matchId: Int,
    val date: String,
    val winTeamId: Int,
    val firstTeamName: String,
    val goalsFirstTeam: Int,
    val secondTeamName: String,
    val goalsSecondTeam: Int,
    val firstTeamId: Int,
    val secondTeamId: Int
)
