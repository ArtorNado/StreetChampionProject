package com.example.streetchampionproject.common.database.userData

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.streetchampionproject.api.scs.models.Teams

@Entity(tableName = "user_data")
class UserDataEntity(

    @PrimaryKey
    val userId: Int,

    val userFirstName: String,

    val userSecondName: String,

    val userGender: String,

    val userCity: String,

    @Embedded
    val team: Teams

)
