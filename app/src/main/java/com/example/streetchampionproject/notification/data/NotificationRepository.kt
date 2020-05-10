package com.example.streetchampionproject.notification.data

import com.example.streetchampionproject.api.scs.models.Notification
import com.example.streetchampionproject.api.scs.models.StreetChampionResponse
import io.reactivex.Single

interface NotificationRepository {

    fun getNotification(recipientId: Int): Single<List<Notification>>

    fun sendAnswerToNotif(notificationId: Int, answer: Int): Single<StreetChampionResponse>
}
