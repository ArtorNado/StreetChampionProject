package com.example.streetchampionproject.settings.editData.domain.interfaces

import io.reactivex.Completable

interface EditDataInteractor {

    fun editData(userFirstName: String, userSecondName: String, userCity: String): Completable

    fun changePassword(currentPassword: String, newPassword: String): Completable
}
