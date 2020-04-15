package com.example.streetchampionproject.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.login.domain.interfaces.LoginInteractor
import javax.inject.Singleton

@Singleton
class LoginViewModelFactory(
    private val loginInteractor: LoginInteractor,
    private val navigator: Navigator
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T?>): T {
        return if (modelClass == LoginViewModel::class.java) {
            LoginViewModel(
                loginInteractor,
                navigator
            ) as T
        } else {
            throw IllegalArgumentException("Invalid view model type")
        }
    }
}
