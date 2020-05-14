package com.example.streetchampionproject.common.di

import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
