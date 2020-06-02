package com.example.streetchampionproject.login.data

import com.example.streetchampionproject.api.scs.models.AuthToken
import com.example.streetchampionproject.common.domain.Exceptions
import com.example.streetchampionproject.common.domain.ResponseCode
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import com.example.streetchampionproject.login.data.interfaces.LoginRepository
import com.example.streetchampionproject.login.data.models.UserAuth
import com.example.streetchampionproject.login.data.models.UserId
import com.example.streetchampionproject.login.data.network.LoginService
import io.reactivex.Single
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService,
    private val localStorage: LocalStorage
) : LoginRepository {

    override fun logIn(userAuth: UserAuth): Single<AuthToken> =
        loginService.logIn(userAuth)
            .doOnSuccess { localStorage.writeMessage("AuthToken", it.token) }
            .onErrorResumeNext {
                Single.error(onError(it))
            }


    override fun userId(email: String): Single<UserId> =
        loginService.getUserId(email)
            .doOnSuccess { localStorage.writeMessage("userId", it.userId.toString()) }
            .onErrorResumeNext {
                Single.error(onError(it))
            }

    private fun onError(error: Throwable): Exceptions =
        when (error) {
            is UnknownHostException -> Exceptions.error(ResponseCode.INTERNET_ERROR)
            is HttpException -> Exceptions.error(ResponseCode.INVALID_LOG_PAS)
            else -> Exceptions.error(ResponseCode.SERVER_ERROR)
        }


}
