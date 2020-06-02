package com.example.streetchampionproject.registration.data.network

import com.example.streetchampionproject.api.scs.models.StreetChampionResponse
import com.example.streetchampionproject.registration.data.model.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {

    @POST("registration")
    fun registration(
        @Body user: User
    ):
            Single<StreetChampionResponse>


}
