package com.example.streetchampionproject.login.domain

import com.example.streetchampionproject.api.scs.models.AuthToken
import com.example.streetchampionproject.common.sharedPreference.LocalStorage
import com.example.streetchampionproject.login.data.LoginRepositoryImpl
import com.example.streetchampionproject.login.data.models.UserAuth
import com.example.streetchampionproject.login.data.models.UserId
import com.example.streetchampionproject.login.domain.interfaces.LoginInteractor
import io.reactivex.Single
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val loginRepository: LoginRepositoryImpl,
    private val localStorage: LocalStorage
): LoginInteractor {

    override fun logIn(email: String, password: String): Single<AuthToken> =
        loginRepository.logIn(
            UserAuth(
                email,
                password
            )
        )

    override fun userId(email: String): Single<UserId> = loginRepository.userId(email)

    override fun writeInStorage(name: String, message: String) = localStorage.writeMessage(name, message)

}
