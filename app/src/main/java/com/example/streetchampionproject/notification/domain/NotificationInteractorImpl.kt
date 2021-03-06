package com.example.streetchampionproject.notification.domain

import com.example.streetchampionproject.api.scs.models.Notification
import com.example.streetchampionproject.notification.data.NotificationRepository
import io.reactivex.Single
import javax.inject.Inject

class NotificationInteractorImpl @Inject constructor(
    private val notificationRepository: NotificationRepository
) : NotificationInteractor {

    override fun getNotification(recipienId: Int): Single<List<Notification>> =
        notificationRepository.getNotification(recipienId)

    override fun sendNotificationAnswer(
        notification: Notification,
        notifications: List<Notification>?
    ): Single<List<Notification>> =
        notificationRepository.sendAnswerToNotif(
            notification.notificationId,
            notification.notificationStatus
        )
            .flatMap {
                deleteElement(notification, notifications)
            }
            .onErrorResumeNext { deleteElement(notification, notifications) }


    private fun deleteElement(
        notification: Notification,
        notifications: List<Notification>?
    ): Single<List<Notification>> {
        val arList: MutableList<Notification>? = notifications?.toMutableList()
        arList?.remove(notification)
        return Single.just(arList)
    }
}
