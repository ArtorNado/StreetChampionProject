package com.example.streetchampionproject.common.data.databse.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "participants")
data class ParticipantsEntity(
    @PrimaryKey
    val userId: Int,
    val firstName: String,
    val secondName: String,
    val userCity: String,
    val matchId: Int
)
