package com.example.streetchampionproject.common.data.databse.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_status")
data class UserStatusInTeamEntity (
    @PrimaryKey
    val userId: Int,
    val teamId: Int,
    val userStatus: String
)
