package com.example.streetchampionproject.registerActivity.di

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.registerActivity.data.RegisterRepositoryImpl
import com.example.streetchampionproject.registerActivity.data.interfaces.RegisterRepository
import com.example.streetchampionproject.registerActivity.domain.RegisterInteractImpl
import com.example.streetchampionproject.registerActivity.domain.interfaces.RegisterInteract
import com.example.streetchampionproject.registerActivity.presentation.viewModel.ViewModelFactory
import com.example.streetchampionproject.registerActivity.scope.RegisterActivityScope
import dagger.Module
import dagger.Provides

@Module
class RegisterFeatureModule {

    @RegisterActivityScope
    @Provides
    fun provideViewModelFactory(registerInteractor: RegisterInteractImpl, navigator: Navigator) =
        ViewModelFactory(registerInteractor, navigator)

    @RegisterActivityScope
    @Provides
    fun provideRegisterInteractor(registerInteractor: RegisterInteractImpl): RegisterInteract =
        registerInteractor

    @RegisterActivityScope
    @Provides
    fun provideRegisterRepository(registerRepositoryImpl: RegisterRepositoryImpl)
            : RegisterRepository = registerRepositoryImpl
}
