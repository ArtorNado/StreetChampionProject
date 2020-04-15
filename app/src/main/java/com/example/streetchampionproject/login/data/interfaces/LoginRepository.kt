package com.example.streetchampionproject.login.data.interfaces

import com.example.streetchampionproject.api.scs.response.AuthToken
import com.example.streetchampionproject.login.data.UserAuth
import com.example.streetchampionproject.login.data.models.UserId
import io.reactivex.Single

interface LoginRepository {

    fun logIn(userAuth: UserAuth): Single<AuthToken>

    fun userId(email: String): Single<UserId>
}
