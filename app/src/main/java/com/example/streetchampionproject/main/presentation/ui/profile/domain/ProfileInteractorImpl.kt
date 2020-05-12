package com.example.streetchampionproject.main.presentation.ui.profile.domain

import com.example.streetchampionproject.api.scs.models.UserData
import com.example.streetchampionproject.main.presentation.ui.profile.data.interfaces.ProfileRepository
import com.example.streetchampionproject.main.presentation.ui.profile.domain.interfaces.ProfileInteractor
import io.reactivex.Single
import javax.inject.Inject

class ProfileInteractorImpl @Inject constructor(
    private val profileRepository: ProfileRepository
): ProfileInteractor {

    override fun getUserData(userId: Int): Single<UserData> = profileRepository.getUserData(userId)

}
