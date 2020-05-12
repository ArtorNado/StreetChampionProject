package com.example.streetchampionproject.common.di

import android.content.Context
import androidx.room.Room
import com.example.streetchampionproject.app.di.scope.ApplicationScope
import com.example.streetchampionproject.common.database.Database
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @ApplicationScope
    @Provides
    fun provideDataBase(context: Context) =
        Room.databaseBuilder(context, Database::class.java, Database.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @ApplicationScope
    @Provides
    fun provideUserDataDao(database: Database) = database.userDataDao()

}

