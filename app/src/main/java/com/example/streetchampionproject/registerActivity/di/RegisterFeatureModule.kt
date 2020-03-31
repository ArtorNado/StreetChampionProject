package com.example.streetchampionproject.registerActivity.di

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.registerActivity.data.RegisterRepository
import com.example.streetchampionproject.registerActivity.domain.RegisterInteract
import com.example.streetchampionproject.registerActivity.presentation.viewModel.ViewModelFactory
import com.example.streetchampionproject.registerActivity.scope.RegisterActivityScope
import dagger.Module
import dagger.Provides

@Module
class RegisterFeatureModule {

    @RegisterActivityScope
    @Provides
    fun provideViewModelFactory(registerInteractor: RegisterInteract, navigator: Navigator) =
        ViewModelFactory(registerInteractor, navigator)

    @RegisterActivityScope
    @Provides
    fun provideRegisterInteractor(registerRepository: RegisterRepository) =
        RegisterInteract(registerRepository)

    @RegisterActivityScope
    @Provides
    fun provideRegisterRepository(streetChampionService: StreetChampionService) = RegisterRepository(streetChampionService)
}
