package com.example.streetchampionproject.main.presentation.ui.profile.domain.interfaces

import com.example.streetchampionproject.main.presentation.ui.profile.data.model.UserData
import io.reactivex.Single

interface ProfileInteractor {

    fun getUserData(userId: Int): Single<UserData>
}
