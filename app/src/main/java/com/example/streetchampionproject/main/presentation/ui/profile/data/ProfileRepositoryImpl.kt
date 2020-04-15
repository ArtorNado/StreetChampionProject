package com.example.streetchampionproject.main.presentation.ui.profile.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.main.presentation.ui.profile.data.interfaces.ProfileRepository
import com.example.streetchampionproject.main.presentation.ui.profile.data.model.UserData
import io.reactivex.Single
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private var streetChampionService: StreetChampionService)
    : ProfileRepository {

    override fun getUserData(userId: String): Single<UserData> = streetChampionService.getUser(userId)
}
