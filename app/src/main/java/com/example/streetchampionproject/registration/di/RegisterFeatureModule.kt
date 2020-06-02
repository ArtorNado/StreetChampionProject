package com.example.streetchampionproject.registration.di

import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.api.apiFactory.ApiFactory
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import com.example.streetchampionproject.login.data.LoginRepositoryImpl
import com.example.streetchampionproject.login.data.interfaces.LoginRepository
import com.example.streetchampionproject.registration.data.RegisterRepositoryImpl
import com.example.streetchampionproject.registration.data.interfaces.RegisterRepository
import com.example.streetchampionproject.registration.di.scope.RegisterActivityScope
import com.example.streetchampionproject.registration.domain.RegisterInteractImpl
import com.example.streetchampionproject.registration.domain.interfaces.RegisterInteract
import com.example.streetchampionproject.registration.presentation.RegisterViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class RegisterFeatureModule {

    @RegisterActivityScope
    @Provides
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    fun provideRegisterViewModel(registerInteractor: RegisterInteract): ViewModel {
        return RegisterViewModel(
            registerInteractor
        )
    }

    @RegisterActivityScope
    @Provides
    fun provideRegisterInteractor(registerInteractor: RegisterInteractImpl): RegisterInteract =
        registerInteractor

    @RegisterActivityScope
    @Provides
    fun provideRegisterRepository(registerRepositoryImpl: RegisterRepositoryImpl)
            : RegisterRepository = registerRepositoryImpl

    @RegisterActivityScope
    @Provides
    fun provideLoginRepository(loginRepository: LoginRepositoryImpl)
            : LoginRepository = loginRepository

    @RegisterActivityScope
    @Provides
    fun provideLoginService(apiFactory: ApiFactory) = apiFactory.loginService

    @RegisterActivityScope
    @Provides
    fun provideRegisterService(apiFactory: ApiFactory) = apiFactory.registerService
}
