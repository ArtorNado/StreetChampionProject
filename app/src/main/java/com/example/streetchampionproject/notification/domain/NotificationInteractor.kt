package com.example.streetchampionproject.notification.domain

import com.example.streetchampionproject.api.scs.models.Notification
import com.example.streetchampionproject.api.scs.models.StreetChampionResponse
import io.reactivex.Single

interface NotificationInteractor {

    fun getNotification(recipienId: Int): Single<List<Notification>>

    fun sendNotificationAnswer(
        notification: Notification,
        notifications: List<Notification>?
    ): Single<StreetChampionResponse>

    fun deleteElement(
        notification: Notification,
        notifications: List<Notification>?
    ): Single<List<Notification>>


}