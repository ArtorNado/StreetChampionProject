package com.example.streetchampionproject.main.presentation.ui.profile.data.interfaces

import com.example.streetchampionproject.api.scs.models.UserData
import io.reactivex.Single

interface ProfileRepository {

    fun getUserData(userId: Int): Single<UserData>

    fun setUserDataLocal(userData: UserData): UserData

    fun getUserDataLocal(userId: Int): Single<UserData>
}
