package com.example.streetchampionproject.app.injector

import androidx.fragment.app.Fragment
import com.example.streetchampionproject.app.App
import com.example.streetchampionproject.app.di.AppComponent
import com.example.streetchampionproject.app.di.DaggerAppComponent
import com.example.streetchampionproject.clubPage.di.ClubPageFeatureComponent
import com.example.streetchampionproject.clubPage.presentation.ui.overview.di.OverviewFeatureComponent
import com.example.streetchampionproject.clubPage.presentation.ui.squad.di.SquadFeatureComponent
import com.example.streetchampionproject.commandMatch.di.CommandMatchFeatureComponent
import com.example.streetchampionproject.creating.createMatch.di.CreateMatchFeatureComponent
import com.example.streetchampionproject.creating.createTeam.di.CreateTeamFeatureComponent
import com.example.streetchampionproject.login.di.LoginFeatureComponent
import com.example.streetchampionproject.main.presentation.ui.clubs.di.ClubListFeatureComponent
import com.example.streetchampionproject.main.presentation.ui.profile.di.ProfileFeatureComponent
import com.example.streetchampionproject.match.di.MatchListFeatureComponent
import com.example.streetchampionproject.notification.di.NotificationFeatureComponent
import com.example.streetchampionproject.registration.di.RegisterFeatureComponent
import com.example.streetchampionproject.singleMatch.di.interfaces.ParticipantListFeatureComponent
import com.example.streetchampionproject.singleMatch.di.interfaces.SingleMatchFeatureComponent

object Injector {

    lateinit var appComponent: AppComponent

    private var loginFeatureComponent: LoginFeatureComponent? = null

    private var registerFeatureComponent: RegisterFeatureComponent? = null

    private var profileFeatureComponent: ProfileFeatureComponent? = null

    private var clubPageFeatureComponent: ClubPageFeatureComponent? = null

    private var squadFeatureComponent: SquadFeatureComponent? = null

    private var clubListFeatureComponent: ClubListFeatureComponent? = null

    private var overviewFeatureComponent: OverviewFeatureComponent? = null

    private var notificationFeatureComponent: NotificationFeatureComponent? = null

    private var createTeamFeatureComponent: CreateTeamFeatureComponent? = null

    private var matchListFeatureComponent: MatchListFeatureComponent? = null

    private var singleMatchFeatureComponent: SingleMatchFeatureComponent? = null

    private var participantListFeatureComponent: ParticipantListFeatureComponent? = null

    private var commandMatchFeatureComponent: CommandMatchFeatureComponent? = null

    private var createMatchFeatureComponent: CreateMatchFeatureComponent? = null

    fun init(app: App) {
        appComponent = DaggerAppComponent.builder()
            .application(app)
            .build()
    }

    fun plusLoginFeatureComponent(): LoginFeatureComponent = loginFeatureComponent
        ?: appComponent.loginFeatureComponent()
            .build().also {
                loginFeatureComponent = it
            }

    fun clearLoginFeatureComponent() {
        loginFeatureComponent = null
    }

    fun plusRegiserFeatureComponent(): RegisterFeatureComponent = registerFeatureComponent
        ?: appComponent.registerFeatureComponent()
            .build().also {
                registerFeatureComponent = it
            }

    fun clearRegisterFeatureComponent() {
        registerFeatureComponent = null
    }

    fun plusProfileFeatureComponent(userId: Int, fragment: Fragment): ProfileFeatureComponent =
        profileFeatureComponent
            ?: appComponent.profileFeatureComponent().withFragment(fragment).create(userId)
                .build().also {
                    profileFeatureComponent = it
                }


    fun clearProfileFeatureComponent() {
        profileFeatureComponent = null
    }

    fun plusClubPageFeatureComponent(id: Int, fragment: Fragment): ClubPageFeatureComponent = clubPageFeatureComponent
        ?: appComponent.provideClubPageFeatureComponent()
            .withFragment(fragment).create(id).build().also {
                clubPageFeatureComponent = it
            }


    fun clearClubPageFeatureComponent() {
        clubPageFeatureComponent = null
    }

    fun plusSquadFeatureComponent(teamId: Int, fragment: Fragment): SquadFeatureComponent = squadFeatureComponent
        ?: appComponent.provideSquadFeatureComponent()
            .withFragment(fragment).create(teamId).build().also {
                squadFeatureComponent = it
            }


    fun clearSquadFeatureComponent() {
        squadFeatureComponent = null
    }

    fun plusClubListFeatureComponent(fragment: Fragment): ClubListFeatureComponent =
        clubListFeatureComponent
            ?: appComponent.provideClubListFeatureComponent()
                .withFragment(fragment).build().also {
                    clubListFeatureComponent = it
                }


    fun clearClubListFeatureComponent() {
        clubListFeatureComponent = null
    }

    fun plusOverviewFeatureComponent(teamId: Int, fragment: Fragment): OverviewFeatureComponent =
        overviewFeatureComponent
            ?: appComponent.provideOverviewFeatureComponent()
                .withFragment(fragment).create(teamId).build().also {
                    overviewFeatureComponent = it
                }

    fun clearOverviewFeatureComponent() {
        overviewFeatureComponent = null
    }

    fun plusNotificationFueatureComponent(recipientId: Int, fragment: Fragment): NotificationFeatureComponent =
        notificationFeatureComponent
            ?: appComponent.provideNotificationFeatureComponent()
                .withFragment(fragment).create(recipientId).build().also {
                    notificationFeatureComponent = it
                }

    fun clearNotificationFeatureComponent() {
        notificationFeatureComponent = null
    }

    fun plusCreateTeamFeatureComponent(fragment: Fragment): CreateTeamFeatureComponent =
        createTeamFeatureComponent
            ?: appComponent.provideCreateTeamFeatureComponent()
                .withFragment(fragment).build().also {
                    createTeamFeatureComponent = it
                }

    fun clearCreateTeamFeatureComponent() {
        createTeamFeatureComponent = null
    }

    fun plusMatchListFeatureComponent(fragment: Fragment): MatchListFeatureComponent =
        matchListFeatureComponent
            ?: appComponent.provideMatchListFeatureComponent()
                .withFragment(fragment).build().also {
                    matchListFeatureComponent = it
                }

    fun clearMatchListFeatureComponent() {
        matchListFeatureComponent = null
    }

    fun plusSingleMatchFeatureComponent(id: Int, fragment: Fragment): SingleMatchFeatureComponent =
        singleMatchFeatureComponent
            ?: appComponent.provideSingleMatchFeatureComponent()
                .withFragment(fragment).create(id).build().also {
                    singleMatchFeatureComponent = it
                }

    fun clearSingleMatchFeatureComponent() {
        singleMatchFeatureComponent = null
    }

    fun plusParticipantListFeatureComponent(matchId: Int, fragment: Fragment): ParticipantListFeatureComponent =
        participantListFeatureComponent
            ?: appComponent.provideParticipantListFeatureComponent()
                .withFragment(fragment).create(matchId).build().also {
                    participantListFeatureComponent = it
                }

    fun clearParticipantListFeatureComponent() {
        participantListFeatureComponent = null
    }

    fun plusCommandMatchFeatureComponent(id: Int, fragment: Fragment): CommandMatchFeatureComponent =
        commandMatchFeatureComponent
            ?: appComponent.provideCommandMatchFeatureComponent()
                .withFragment(fragment).create(id).build().also {
                    commandMatchFeatureComponent = it
                }

    fun clearCommandMatchFeatureComponent() {
        commandMatchFeatureComponent = null
    }

    fun plusCreateMatchFeatureComponent(fragment: Fragment): CreateMatchFeatureComponent =
        createMatchFeatureComponent
            ?: appComponent.provideCreateMatchFeatureComponent()
                .withFragment(fragment).build().also {
                    createMatchFeatureComponent = it
                }

    fun clearCreateMatchFeatureComponent() {
        createMatchFeatureComponent = null
    }
}
