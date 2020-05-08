package com.example.streetchampionproject.notification.presentation

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.api.scs.response.Notification
import com.example.streetchampionproject.notification.domain.NotificationInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NotificationViewModel(
    private val notificationInteractor: NotificationInteractor,
    private val recipientId: Int
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _pgStatus = MutableLiveData<Int>()
    val pgStatus: LiveData<Int> = _pgStatus

    private var _notifications = MutableLiveData<List<Notification>>()
    var notifications: MutableLiveData<List<Notification>> = _notifications


    fun getData() {
        _pgStatus.value = View.VISIBLE
        compositeDisposable.add(
            notificationInteractor.getNotification(recipientId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _notifications.value = result
                    _pgStatus.value = View.GONE
                },
                    { error ->
                        Log.e("NOTIFICATION_ERROR", error.toString())
                        _pgStatus.value = View.GONE
                    })
        )

    }

    fun notifAnswer(notification: Notification) {
        compositeDisposable.add(
            notificationInteractor.sendNotificationAnswer(notification, _notifications.value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.e("RESULT_ANSW", result.toString())
                },
                    { error ->
                        Log.e("NOTIFICATION_ERROR", error.toString())
                    })
        )
        compositeDisposable.add(
            notificationInteractor.deleteElement(notification, _notifications.value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _notifications.value = result
                },
                    { error ->
                        Log.e("DEL_ER", error.toString())
                    })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
