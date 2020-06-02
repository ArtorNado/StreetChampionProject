package com.example.streetchampionproject.registration.domain

import com.example.streetchampionproject.login.data.interfaces.LoginRepository
import com.example.streetchampionproject.login.data.models.UserAuth
import com.example.streetchampionproject.login.data.models.UserId
import com.example.streetchampionproject.registration.data.RegisterRepositoryImpl
import com.example.streetchampionproject.registration.data.model.User
import com.example.streetchampionproject.registration.domain.interfaces.RegisterInteract
import io.reactivex.Single
import javax.inject.Inject

class RegisterInteractImpl
@Inject constructor(
    private val registerRepository: RegisterRepositoryImpl,
    private val loginRepository: LoginRepository
) : RegisterInteract {

    override fun registration(u: User): Single<UserId> =
        registerRepository.registration(u)
            .flatMap {
                loginRepository.logIn(UserAuth(u.userLogin, u.userPassword))
            }
            .flatMap {
                loginRepository.userId(u.userLogin)
            }
}
