package com.example.streetchampionproject.common.data.databse.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class TeamsEntity(

    @PrimaryKey
    val teamId: Int,

    val teamName: String,

    val teamCity: String,

    val creatorId: Int

)
