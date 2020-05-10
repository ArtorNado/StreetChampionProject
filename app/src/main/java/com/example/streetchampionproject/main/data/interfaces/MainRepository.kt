package com.example.streetchampionproject.main.data.interfaces

import com.example.streetchampionproject.api.scs.models.UserData
import io.reactivex.Single

interface MainRepository {

    fun getUserData(userId: String): Single<UserData>
}
