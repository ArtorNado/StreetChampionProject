package com.example.streetchampionproject.app

import android.app.Application
import androidx.fragment.app.Fragment
import com.example.streetchampionproject.app.injector.Injector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class App: Application(){

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }

}
