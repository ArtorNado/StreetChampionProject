package com.example.streetchampionproject.common.data.databse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.streetchampionproject.common.data.databse.dao.SquadDao
import com.example.streetchampionproject.common.data.databse.dao.TeamsDao
import com.example.streetchampionproject.common.data.databse.dao.UserDataDao
import com.example.streetchampionproject.common.data.databse.dao.UserStatusInTeamDao
import com.example.streetchampionproject.common.data.databse.models.SquadEntity
import com.example.streetchampionproject.common.data.databse.models.TeamsEntity
import com.example.streetchampionproject.common.data.databse.models.UserDataEntity
import com.example.streetchampionproject.common.data.databse.models.UserStatusInTeamEntity

@Database(
    entities = [UserDataEntity::class, TeamsEntity::class, UserStatusInTeamEntity::class, SquadEntity::class],
    version = 5,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun userDataDao(): UserDataDao

    abstract fun teamsDao(): TeamsDao

    abstract fun userStatusInTeamDao(): UserStatusInTeamDao

    abstract fun squadDao(): SquadDao

    companion object {
        const val DATABASE_NAME = "My_database"
    }
}
