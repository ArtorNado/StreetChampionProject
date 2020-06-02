package com.example.streetchampionproject.notification.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.api.scs.models.Notification
import com.example.streetchampionproject.common.presentation.CONSTANTS
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.notification.domain.NotificationInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NotificationViewModel(
    private val notificationInteractor: NotificationInteractor,
    private val recipientId: Int
) : BaseViewModel() {

    private val _pgStatus by lazy { MutableLiveData<String>() }
    val pgStatus: LiveData<String> = _pgStatus

    private val _notifications by lazy { MutableLiveData<List<Notification>>() }
    var notifications: MutableLiveData<List<Notification>> = _notifications


    fun getData() {
        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            notificationInteractor.getNotification(recipientId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _notifications.value = result
                    _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                },
                    { error ->
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    })
        )

    }

    fun notifAnswer(notification: Notification) {
        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            notificationInteractor.sendNotificationAnswer(notification, _notifications.value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _notifications.value = result
                    _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                },
                    { error ->
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                        onError(error)
                    })
        )
    }
}
