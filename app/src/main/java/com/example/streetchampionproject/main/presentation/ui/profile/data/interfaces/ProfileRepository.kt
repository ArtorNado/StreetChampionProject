package com.example.streetchampionproject.main.presentation.ui.profile.data.interfaces

import com.example.streetchampionproject.main.presentation.ui.profile.data.model.UserData
import io.reactivex.Single

interface ProfileRepository {

    fun getUserData(userId: String): Single<UserData>
}
