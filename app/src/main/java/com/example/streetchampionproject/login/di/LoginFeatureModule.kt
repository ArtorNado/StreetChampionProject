package com.example.streetchampionproject.login.di

import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.api.apiFactory.ApiFactory
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import com.example.streetchampionproject.login.data.LoginRepositoryImpl
import com.example.streetchampionproject.login.data.interfaces.LoginRepository
import com.example.streetchampionproject.login.di.scope.LoginActivityScope
import com.example.streetchampionproject.login.domain.LoginInteractorImpl
import com.example.streetchampionproject.login.domain.interfaces.LoginInteractor
import com.example.streetchampionproject.login.presentation.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class LoginFeatureModule {

    @LoginActivityScope
    @Provides
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun provideCountryListViewModel(
        loginInteractor: LoginInteractor
    ): ViewModel {
        return LoginViewModel(
            loginInteractor
        )
    }

    @LoginActivityScope
    @Provides
    fun provideService(apiFactory: ApiFactory) = apiFactory.loginService

    @LoginActivityScope
    @Provides
    fun provideLoginInteractor(loginInteractor: LoginInteractorImpl): LoginInteractor =
        loginInteractor

    @LoginActivityScope
    @Provides
    fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository =
        loginRepositoryImpl

}
