package com.example.streetchampionproject.login.domain.interfaces

import com.example.streetchampionproject.login.data.models.UserId
import io.reactivex.Single

interface LoginInteractor {

    fun logIn(email: String, password: String): Single<UserId>

}
