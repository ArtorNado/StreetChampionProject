package com.example.streetchampionproject.registration.data.interfaces

import com.example.streetchampionproject.api.scs.models.StreetChampionResponse
import com.example.streetchampionproject.registration.data.model.User
import io.reactivex.Single

interface RegisterRepository {

    fun registration(u: User): Single<StreetChampionResponse>

}
