package com.example.streetchampionproject.common.data.databse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.streetchampionproject.common.data.databse.dao.*
import com.example.streetchampionproject.common.data.databse.models.*

@Database(
    entities = [UserDataEntity::class, TeamsEntity::class, UserStatusInPlaceEntity::class,
        SquadEntity::class, MatchSingleEntity::class, ParticipantsEntity::class],
    version = 8,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun userDataDao(): UserDataDao

    abstract fun teamsDao(): TeamsDao

    abstract fun userStatusInPlaceDao(): UserStatusInPlaceDao

    abstract fun squadDao(): SquadDao

    abstract fun matchListDao(): MatchSingleDao

    abstract fun participantsDao(): ParticipantsDao

    companion object {
        const val DATABASE_NAME = "My_database"
    }
}
