package com.example.streetchampionproject.settings.editData.data.network

import com.example.streetchampionproject.api.scs.models.EditData
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EditDataService {

    @GET("changePassword")
    fun changePassword(
        @Query("userId") userId: Int,
        @Query("currentPassword") currentPassword: String,
        @Query("newPassword") newPassword: String
    ):
            Completable

    @POST("editData")
    fun editData(
        @Body editData: EditData
    ): Completable

}
