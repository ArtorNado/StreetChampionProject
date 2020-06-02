package com.example.streetchampionproject.settings.editData.data

import com.example.streetchampionproject.api.scs.models.EditData
import com.example.streetchampionproject.common.domain.Exceptions
import com.example.streetchampionproject.common.domain.ResponseCode
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import com.example.streetchampionproject.settings.editData.data.interfaces.EditDataRepository
import com.example.streetchampionproject.settings.editData.data.network.EditDataService
import io.reactivex.Completable
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class EditDataRepositoryImpl @Inject constructor(
    private val editDataService: EditDataService,
    private val localStorage: LocalStorage
) : EditDataRepository {

    val userId = localStorage.readMessage("userId")

    override fun editData(editData: EditData): Completable {
        editData.userId = userId.toString().toInt()
        return editDataService.editData(editData)
            .onErrorResumeNext { error ->
                when (error) {
                    is HttpException -> Completable.error(Exceptions.error(ResponseCode.DATA_ERROR))
                    else -> Completable.error(onError(error))
                }
            }
    }

    override fun changePassword(currentPassword: String, newPassword: String): Completable =
        editDataService.changePassword(userId.toString().toInt(), currentPassword, newPassword)
            .onErrorResumeNext { error ->
                when (error) {
                    is HttpException -> Completable.error(Exceptions.error(ResponseCode.CURRENT_PASSWORD_ERROR))
                    else -> Completable.error(onError(error))
                }
            }

    private fun onError(error: Throwable) =
        when (error) {
            is UnknownHostException -> Exceptions.error(ResponseCode.INTERNET_ERROR)
            else -> Exceptions.error(ResponseCode.SERVER_ERROR)
        }
}
