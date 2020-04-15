package com.example.streetchampionproject.main.domain.interfaces

import com.example.streetchampionproject.main.presentation.ui.profile.data.model.UserData
import io.reactivex.Single

interface MainInteractor {

    fun getUserData(userId: Int): Single<UserData>
}
