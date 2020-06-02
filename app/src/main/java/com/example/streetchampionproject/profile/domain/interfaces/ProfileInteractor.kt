package com.example.streetchampionproject.profile.domain.interfaces

import com.example.streetchampionproject.api.scs.models.UserData
import io.reactivex.Completable
import io.reactivex.Observable

interface ProfileInteractor {

    fun getUserData(userId: Int): Observable<UserData>

    fun updateUserData(userId: Int): Completable

}
