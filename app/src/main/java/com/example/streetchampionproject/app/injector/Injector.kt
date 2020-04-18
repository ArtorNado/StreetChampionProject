package com.example.streetchampionproject.app.injector

import com.example.streetchampionproject.app.App
import com.example.streetchampionproject.app.di.AppComponent
import com.example.streetchampionproject.app.di.DaggerAppComponent
import com.example.streetchampionproject.clubPage.di.ClubPageFeatureComponent
import com.example.streetchampionproject.clubPage.presentation.ui.squad.di.SquadFeatureComponent
import com.example.streetchampionproject.login.di.LoginFeatureComponent
import com.example.streetchampionproject.main.presentation.ui.profile.di.ProfileFeatureComponent
import com.example.streetchampionproject.registration.di.RegisterFeatureComponent

object Injector {

    lateinit var appComponent: AppComponent
    private var loginFeatureComponent: LoginFeatureComponent? = null
    private var registerFeatureComponent: RegisterFeatureComponent? = null
    private var profileFeatureComponent: ProfileFeatureComponent? = null
    private var clubPageFeatureComponent: ClubPageFeatureComponent? = null
    private var squadFeatureComponent: SquadFeatureComponent? = null


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

    fun plusProfileFeatureComponent(): ProfileFeatureComponent = profileFeatureComponent
        ?: appComponent.profileFeatureComponent()
            .build().also {
                profileFeatureComponent = it
            }


    fun clearProfileFeatureComponent() {
        profileFeatureComponent = null
    }

    fun plusClubPageFeatureComponent(): ClubPageFeatureComponent = clubPageFeatureComponent
        ?: appComponent.provideClubPageFeatureComponent()
            .build().also {
                clubPageFeatureComponent = it
            }


    fun clearClubPageFeatureComponent() {
        clubPageFeatureComponent = null
    }

    fun plusSquadFeatureComponent(): SquadFeatureComponent = squadFeatureComponent
        ?: appComponent.provideSquadFeatureComponent()
            .build().also {
                squadFeatureComponent = it
            }


    fun clearSquadFeatureComponent() {
        squadFeatureComponent = null
    }

}
