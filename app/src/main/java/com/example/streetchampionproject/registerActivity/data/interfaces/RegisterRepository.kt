package com.example.streetchampionproject.registerActivity.data.interfaces

import com.example.streetchampionproject.api.scs.response.StreetChampionResponse
import com.example.streetchampionproject.registerActivity.data.model.User
import io.reactivex.Single

interface RegisterRepository {

    fun registration(u: User): Single<StreetChampionResponse>

}
