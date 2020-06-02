package com.example.streetchampionproject.profile.domain

import com.example.streetchampionproject.api.scs.models.UserData
import com.example.streetchampionproject.profile.data.interfaces.ProfileRepository
import com.example.streetchampionproject.profile.domain.interfaces.ProfileInteractor
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProfileInteractorImpl @Inject constructor(
    private val profileRepository: ProfileRepository
) : ProfileInteractor {

    override fun getUserData(userId: Int): Observable<UserData> =
        profileRepository.getUserDataLocal(userId)

    override fun updateUserData(userId: Int): Completable =
        profileRepository.updateUserData(userId)

}
