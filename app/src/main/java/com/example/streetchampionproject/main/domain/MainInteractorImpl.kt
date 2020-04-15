package com.example.streetchampionproject.main.domain

import com.example.streetchampionproject.main.data.interfaces.MainRepository
import com.example.streetchampionproject.main.domain.interfaces.MainInteractor
import com.example.streetchampionproject.main.presentation.ui.profile.data.model.UserData
import io.reactivex.Single
import javax.inject.Inject

class MainInteractorImpl @Inject constructor(
    private val mainRepository: MainRepository
): MainInteractor {

    override fun getUserData(userId: Int): Single<UserData> = mainRepository.getUserData(userId.toString())

}
