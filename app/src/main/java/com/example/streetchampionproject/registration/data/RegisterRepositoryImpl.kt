package com.example.streetchampionproject.registration.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.models.StreetChampionResponse
import com.example.streetchampionproject.common.domain.Exceptions
import com.example.streetchampionproject.common.domain.ResponseCode
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import com.example.streetchampionproject.registration.data.interfaces.RegisterRepository
import com.example.streetchampionproject.registration.data.model.User
import io.reactivex.Single
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService,
    private val localStorage: LocalStorage
) : RegisterRepository {

    override fun registration(u: User): Single<StreetChampionResponse> =
        streetChampionService.registration(u)
            .onErrorResumeNext {
                Single.error(onError(it))
            }

    private fun onError(error: Throwable): Exceptions =
        when (error) {
            is UnknownHostException -> Exceptions.error(ResponseCode.INTERNET_ERROR)
            is HttpException -> Exceptions.error(ResponseCode.LOGIN_ALREADY_EXIST)
            else -> Exceptions.error(ResponseCode.SERVER_ERROR)
        }

}
