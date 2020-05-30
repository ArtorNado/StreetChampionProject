package com.example.streetchampionproject.login.domain

import com.example.streetchampionproject.login.data.LoginRepositoryImpl
import com.example.streetchampionproject.login.data.models.UserAuth
import com.example.streetchampionproject.login.data.models.UserId
import com.example.streetchampionproject.login.domain.interfaces.LoginInteractor
import io.reactivex.Single
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val loginRepository: LoginRepositoryImpl
) : LoginInteractor {

    override fun logIn(email: String, password: String): Single<UserId> =
        loginRepository.logIn(
            UserAuth(
                email,
                password
            )
        )
            .flatMap {
                loginRepository.userId(email)
            }

}
