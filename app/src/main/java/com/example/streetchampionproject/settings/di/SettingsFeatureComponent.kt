package com.example.streetchampionproject.settings.di

import com.example.streetchampionproject.settings.SettingsFragment
import com.example.streetchampionproject.settings.di.scope.SettingsScope
import dagger.Subcomponent

@SettingsScope
@Subcomponent()
interface SettingsFeatureComponent {

    fun inject(settingsFragment: SettingsFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): SettingsFeatureComponent

    }
}
