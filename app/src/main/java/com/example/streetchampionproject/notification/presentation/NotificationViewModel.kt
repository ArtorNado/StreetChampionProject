package com.example.streetchampionproject.notification.presentation

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.api.scs.models.Notification
import com.example.streetchampionproject.notification.domain.NotificationInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NotificationViewModel(
    private val notificationInteractor: NotificationInteractor,
    private val recipientId: Int
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _pgStatus by lazy { MutableLiveData<Int>() }
    val pgStatus: LiveData<Int> = _pgStatus

    private val _notifications by lazy { MutableLiveData<List<Notification>>() }
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
        _pgStatus.value = View.VISIBLE
        compositeDisposable.add(
            notificationInteractor.sendNotificationAnswer(notification, _notifications.value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    Log.e("RESULT_ANSW", result.toString())
                    _pgStatus.value = View.GONE
                },
                    { error ->
                        Log.e("NOTIFICATION_ERROR", error.toString())
                        _pgStatus.value = View.GONE
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
