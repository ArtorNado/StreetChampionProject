package com.example.streetchampionproject.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.streetchampionproject.common.database.userData.UserDataDao
import com.example.streetchampionproject.common.database.userData.UserDataEntity

@Database(entities = [UserDataEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun userDataDao(): UserDataDao

    companion object {
        const val DATABASE_NAME = "My_database"
    }
}
