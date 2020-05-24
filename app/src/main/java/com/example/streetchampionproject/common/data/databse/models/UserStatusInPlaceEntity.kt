package com.example.streetchampionproject.common.data.databse.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_status")
data class UserStatusInPlaceEntity (
    @PrimaryKey
    val userId: Int,
    val placeId: Int,
    val userStatus: String
)
