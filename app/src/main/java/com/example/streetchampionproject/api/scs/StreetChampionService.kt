package com.example.streetchampionproject.api.scs

import com.example.streetchampionproject.registerActivity.data.model.User
import com.example.streetchampionproject.api.scs.response.StreetChampionResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface StreetChampionService {

    @POST("registration")
    fun registration(
        @Body u: User
    ):
            Single<StreetChampionResponse>
}
