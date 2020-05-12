package com.example.streetchampionproject.login.di

import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.common.viewModel.ViewModelKey
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
        loginInteractor: LoginInteractor,
        navigator: Navigator
    ): ViewModel {
        return LoginViewModel(
            loginInteractor,
            navigator
        )
    }

    @LoginActivityScope
    @Provides
    fun provideLoginInteractor(loginInteractor: LoginInteractorImpl): LoginInteractor =
        loginInteractor

    @LoginActivityScope
    @Provides
    fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository =
        loginRepositoryImpl
}
