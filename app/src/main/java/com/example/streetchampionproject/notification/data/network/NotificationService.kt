package com.example.streetchampionproject.notification.data.network

import com.example.streetchampionproject.api.scs.models.Notification
import com.example.streetchampionproject.api.scs.models.StreetChampionResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NotificationService {

    @POST("getNotificationByRecipient/{recipientId}")
    fun getNotificationByRecipientId(
        @Path("recipientId") recipientId: Int
    ):
            Single<List<Notification>>

    @GET("answerNotification")
    fun sendAnswerToNotif(
        @Query("notification") notificationId: Int,
        @Query("answer") answer: Int
    ):
            Single<StreetChampionResponse>
}
