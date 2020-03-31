package com.example.streetchampionproject.loginActivity.domain

import android.util.Log
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.loginActivity.data.LoginRepository

class LoginInteractor(
    private val loginRepository: LoginRepository
) {

    fun logIn(email: String, password: String) {
        Log.e("LogInInInteractor", "START")
        loginRepository.logIn(email, password)
    }

}
