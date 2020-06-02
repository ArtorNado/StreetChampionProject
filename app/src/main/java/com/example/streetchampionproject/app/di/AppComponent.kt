package com.example.streetchampionproject.app.di

import com.example.streetchampionproject.api.apiFactory.di.ApiFactoryModule
import com.example.streetchampionproject.app.App
import com.example.streetchampionproject.app.di.scope.ApplicationScope
import com.example.streetchampionproject.clubPage.di.ClubPageFeatureComponent
import com.example.streetchampionproject.clubs.di.ClubListFeatureComponent
import com.example.streetchampionproject.commandMatch.di.CommandMatchFeatureComponent
import com.example.streetchampionproject.common.di.DatabaseModule
import com.example.streetchampionproject.common.di.LocalStorageModule
import com.example.streetchampionproject.common.di.ViewModelFactoryModule
import com.example.streetchampionproject.creating.createMatch.di.CreateMatchFeatureComponent
import com.example.streetchampionproject.creating.createTeam.di.CreateTeamFeatureComponent
import com.example.streetchampionproject.login.di.LoginFeatureComponent
import com.example.streetchampionproject.matchHistory.di.MatchHistoryFeatureComponent
import com.example.streetchampionproject.matches.di.MatchListFeatureComponent
import com.example.streetchampionproject.notification.di.NotificationFeatureComponent
import com.example.streetchampionproject.profile.di.ProfileFeatureComponent
import com.example.streetchampionproject.registration.di.RegisterFeatureComponent
import com.example.streetchampionproject.settings.di.SettingsFeatureComponent
import com.example.streetchampionproject.settings.editData.di.EditDataFeatureComponent
import com.example.streetchampionproject.singleMatch.di.interfaces.ParticipantListFeatureComponent
import com.example.streetchampionproject.singleMatch.di.interfaces.SingleMatchFeatureComponent
import com.example.streetchampionproject.teamSquad.di.SquadFeatureComponent
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [AppModule::class, NavigatorModule::class, ApiFactoryModule::class,
        ViewModelFactoryModule::class, LocalStorageModule::class, DatabaseModule::class]
)
interface AppComponent {

    fun loginFeatureComponent(): LoginFeatureComponent.Builder

    fun registerFeatureComponent(): RegisterFeatureComponent.Builder

    fun profileFeatureComponent(): ProfileFeatureComponent.Builder

    fun provideClubPageFeatureComponent(): ClubPageFeatureComponent.Builder

    fun provideSquadFeatureComponent(): SquadFeatureComponent.Builder

    fun provideClubListFeatureComponent(): ClubListFeatureComponent.Builder

    fun provideOverviewFeatureComponent(): MatchHistoryFeatureComponent.Builder

    fun provideNotificationFeatureComponent(): NotificationFeatureComponent.Builder

    fun provideCreateTeamFeatureComponent(): CreateTeamFeatureComponent.Builder

    fun provideMatchListFeatureComponent(): MatchListFeatureComponent.Builder

    fun provideSingleMatchFeatureComponent(): SingleMatchFeatureComponent.Builder

    fun provideParticipantListFeatureComponent(): ParticipantListFeatureComponent.Builder

    fun provideCommandMatchFeatureComponent(): CommandMatchFeatureComponent.Builder

    fun provideCreateMatchFeatureComponent(): CreateMatchFeatureComponent.Builder

    fun provideEditDataFeatureComponent(): EditDataFeatureComponent.Builder

    fun provideSettingsFeatureComponent(): SettingsFeatureComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}
