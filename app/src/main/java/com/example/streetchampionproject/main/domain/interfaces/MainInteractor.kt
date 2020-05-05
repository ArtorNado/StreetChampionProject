package com.example.streetchampionproject.main.domain.interfaces

import com.example.streetchampionproject.api.scs.response.UserData
import io.reactivex.Single

interface MainInteractor {

    fun getUserData(userId: Int): Single<UserData>
}
