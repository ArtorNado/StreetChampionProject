package com.example.streetchampionproject.loginActivity.di

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.loginActivity.data.LoginRepository
import com.example.streetchampionproject.loginActivity.domain.LoginInteractor
import com.example.streetchampionproject.loginActivity.presentation.viewModel.ViewModelFactory
import com.example.streetchampionproject.loginActivity.scope.LoginActivityScope
import dagger.Module
import dagger.Provides

@Module
class LoginFeatureModule {

    @LoginActivityScope
    @Provides
    fun provideViewModelFactory(loginInteractor: LoginInteractor, navigator: Navigator) =
        ViewModelFactory(loginInteractor, navigator)

    @LoginActivityScope
    @Provides
    fun provideLoginInteractor(loginRepository: LoginRepository) = LoginInteractor(loginRepository)

    @LoginActivityScope
    @Provides
    fun provideLoginRepository(streetChampionService: StreetChampionService) = LoginRepository(streetChampionService)
}
