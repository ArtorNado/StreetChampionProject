package com.example.streetchampionproject.registerActivity.domain

import com.example.streetchampionproject.api.scs.response.StreetChampionResponse
import com.example.streetchampionproject.registerActivity.data.RegisterRepositoryImpl
import com.example.streetchampionproject.registerActivity.data.model.User
import com.example.streetchampionproject.registerActivity.domain.interfaces.RegisterInteract
import io.reactivex.Single
import javax.inject.Inject

class RegisterInteractImpl
@Inject constructor(private val registerRepository: RegisterRepositoryImpl) : RegisterInteract {

    override fun registration(u: User): Single<StreetChampionResponse> =
        registerRepository.registration(u)
}
