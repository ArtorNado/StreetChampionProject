package com.example.streetchampionproject.main.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.main.data.interfaces.MainRepository
import com.example.streetchampionproject.main.presentation.ui.profile.data.model.UserData
import io.reactivex.Single
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private var streetChampionService: StreetChampionService)
    : MainRepository {

    override fun getUserData(userId: String): Single<UserData> = streetChampionService.getUser(userId)
}
