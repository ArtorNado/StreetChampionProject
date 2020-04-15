package com.example.streetchampionproject.app.di

import com.example.streetchampionproject.main.presentation.ui.profile.presentation.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    internal abstract fun contributePhotoPinListFragment(): ProfileFragment
}
