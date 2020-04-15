package com.example.streetchampionproject.main.data.interfaces

import com.example.streetchampionproject.main.presentation.ui.profile.data.model.UserData
import io.reactivex.Single

interface MainRepository {

    fun getUserData(userId: String): Single<UserData>
}
