package com.example.streetchampionproject.main.presentation.ui.profile.data.interfaces

import com.example.streetchampionproject.api.scs.models.UserData
import com.example.streetchampionproject.common.data.databse.models.UserDataEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface ProfileRepository {

    fun setUserDataLocal(userDataEntity: UserDataEntity)

    fun getUserDataLocal(userId: Int): Observable<UserData>

    fun updateUserData(userId: Int): Completable
}
