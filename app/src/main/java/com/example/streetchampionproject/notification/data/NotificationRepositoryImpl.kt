package com.example.streetchampionproject.notification.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.models.Notification
import com.example.streetchampionproject.api.scs.models.StreetChampionResponse
import com.example.streetchampionproject.common.domain.Exceptions
import com.example.streetchampionproject.common.domain.ResponseCode
import io.reactivex.Single
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService
) : NotificationRepository {

    override fun getNotification(recipientId: Int): Single<List<Notification>> =
        streetChampionService.getNotificationByRecipientId(recipientId)
            .onErrorResumeNext { error ->
                when (error) {
                    is UnknownHostException -> Single.error(Exceptions.error(ResponseCode.INTERNET_ERROR))
                    else -> Single.error(Exceptions.error(ResponseCode.SERVER_ERROR))
                }
            }

    override fun sendAnswerToNotif(
        notificationId: Int,
        answer: Int
    ): Single<StreetChampionResponse> =
        streetChampionService.sendAnswerToNotif(notificationId, answer)
            .onErrorResumeNext { error ->
                when (error) {
                    is UnknownHostException -> Single.error(Exceptions.error(ResponseCode.INTERNET_ERROR))
                    is HttpException -> Single.error(Exceptions.error(ResponseCode.USER_ALREADY_HAVE_TEAM))
                    else -> Single.error(Exceptions.error(ResponseCode.SERVER_ERROR))
                }
            }
}
