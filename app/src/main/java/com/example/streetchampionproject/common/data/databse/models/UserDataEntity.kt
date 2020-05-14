package com.example.streetchampionproject.common.data.databse.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class UserDataEntity(

    @PrimaryKey
    val userId: Int,

    val userFirstName: String,

    val userSecondName: String,

    val userGender: String,

    val userCity: String,

    val team: String

)
