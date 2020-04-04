package com.example.streetchampionproject.registerActivity.domain

import com.example.streetchampionproject.api.scs.response.StreetChampionResponse
import com.example.streetchampionproject.registerActivity.data.RegisterRepository
import com.example.streetchampionproject.registerActivity.data.model.User
import io.reactivex.Single

class RegisterInteract(private val registerRepository: RegisterRepository) {

    fun registration(u: User): Single<StreetChampionResponse> = registerRepository.registration(u)
}
