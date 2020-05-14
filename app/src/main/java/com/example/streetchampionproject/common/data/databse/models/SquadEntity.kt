package com.example.streetchampionproject.common.data.databse.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "squad")
data class SquadEntity (
    @PrimaryKey
    val userId: Int,
    val firstName: String,
    val secondName: String,
    val userCity: String,
    val teamId: Int
)
