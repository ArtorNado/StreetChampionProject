package com.example.streetchampionproject.notification.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.models.Notification
import com.example.streetchampionproject.api.scs.models.StreetChampionResponse
import io.reactivex.Single
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService
) : NotificationRepository {

    override fun getNotification(recipientId: Int): Single<List<Notification>> =
        streetChampionService.getNotificationByRecipientId(recipientId)

    override fun sendAnswerToNotif(
        notificationId: Int,
        answer: Int
    ): Single<StreetChampionResponse> =
        streetChampionService.sendAnswerToNotif(notificationId, answer)
}
