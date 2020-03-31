package com.example.streetchampionproject.app

import android.app.Application
import com.example.streetchampionproject.app.di.AppComponent
import com.example.streetchampionproject.app.di.DaggerAppComponent

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent =
            DaggerAppComponent.create()
    }


    companion object{

        lateinit var instance: App
        lateinit var appComponent: AppComponent

    }

}
