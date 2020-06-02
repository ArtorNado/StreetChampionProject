package com.example.streetchampionproject.settings.editData.data.interfaces

import com.example.streetchampionproject.api.scs.models.EditData
import io.reactivex.Completable

interface EditDataRepository {

    fun editData(editData: EditData): Completable

    fun changePassword(currentPassword: String, newPassword: String): Completable

}
