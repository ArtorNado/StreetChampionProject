package com.example.streetchampionproject.login.domain.interfaces

import com.example.streetchampionproject.api.scs.models.AuthToken
import com.example.streetchampionproject.login.data.models.UserId
import io.reactivex.Single

interface LoginInteractor {

    fun logIn(email: String, password: String): Single<AuthToken>

    fun userId(email: String): Single<UserId>

    fun writeInStorage(name: String, message: String)

}
