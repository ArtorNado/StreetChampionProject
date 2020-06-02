package com.example.streetchampionproject.settings.editData.domain

import com.example.streetchampionproject.api.scs.models.EditData
import com.example.streetchampionproject.settings.editData.data.interfaces.EditDataRepository
import com.example.streetchampionproject.settings.editData.domain.interfaces.EditDataInteractor
import io.reactivex.Completable
import javax.inject.Inject

class EditDataInteractorImpl @Inject constructor(
    private val editDataRepository: EditDataRepository
) : EditDataInteractor {

    override fun editData(
        userFirstName: String,
        userSecondName: String,
        userCity: String
    ): Completable =
        editDataRepository.editData(EditData(null, userFirstName, userSecondName, userCity))

    override fun changePassword(currentPassword: String, newPassword: String): Completable =
        editDataRepository.changePassword(currentPassword, newPassword)


}
