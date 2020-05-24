package com.example.streetchampionproject.common.di

import android.content.Context
import androidx.room.Room
import com.example.streetchampionproject.app.di.scope.ApplicationScope
import com.example.streetchampionproject.common.data.databse.Database
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

    @ApplicationScope
    @Provides
    fun provideTeamsDao(database: Database) = database.teamsDao()

    @ApplicationScope
    @Provides
    fun provideUserStatusDao(database: Database) = database.userStatusInPlaceDao()

    @ApplicationScope
    @Provides
    fun provideSquadDao(database: Database) = database.squadDao()

    @ApplicationScope
    @Provides
    fun provideMatchSingleDao(database: Database) = database.matchListDao()

    @ApplicationScope
    @Provides
    fun provideParticipantsDao(database: Database) = database.participantsDao()

    @ApplicationScope
    @Provides
    fun provideMatchCommandDao(database: Database) = database.matchCommandDao()

    @ApplicationScope
    @Provides
    fun provideEndedCommandMatchDao(database: Database) = database.endedCommandMatchDao()

}

