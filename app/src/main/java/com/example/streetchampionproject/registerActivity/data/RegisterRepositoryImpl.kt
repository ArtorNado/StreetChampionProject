package com.example.streetchampionproject.registerActivity.data

import android.util.Log
import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.response.StreetChampionResponse
import com.example.streetchampionproject.registerActivity.data.interfaces.RegisterRepository
import com.example.streetchampionproject.registerActivity.data.model.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterRepositoryImpl
@Inject constructor(private var streetChampionService: StreetChampionService): RegisterRepository {

     override fun registration(u: User): Single<StreetChampionResponse> {
        return streetChampionService.registration(u)
    }

}
