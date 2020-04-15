package com.example.streetchampionproject.registration.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.response.StreetChampionResponse
import com.example.streetchampionproject.registration.data.interfaces.RegisterRepository
import com.example.streetchampionproject.registration.data.model.User
import io.reactivex.Single
import javax.inject.Inject

class RegisterRepositoryImpl
@Inject constructor(private var streetChampionService: StreetChampionService) : RegisterRepository {

    override fun registration(u: User): Single<StreetChampionResponse> =
        streetChampionService.registration(u)

}
