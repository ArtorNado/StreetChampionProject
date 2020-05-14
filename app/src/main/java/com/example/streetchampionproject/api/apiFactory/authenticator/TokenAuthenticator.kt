package com.example.streetchampionproject.api.apiFactory.authenticator

import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val localStorage: LocalStorage
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val newAccessToken = localStorage.readMessage("AuthToken")
        return response.request().newBuilder()
            .header("Authorization", newAccessToken)
            .build();
    }

}
