package com.example.streetchampionproject.loginActivity.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.loginActivity.domain.LoginInteractor
import javax.inject.Singleton


@Singleton
class ViewModelFactory(
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
