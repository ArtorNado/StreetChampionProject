package com.example.streetchampionproject.settings.editData.presentation.changePassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.common.presentation.CONSTANTS
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.settings.editData.domain.interfaces.EditDataInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChangePasswordViewModel(
    private val editDataInteractor: EditDataInteractor
) : BaseViewModel() {

    private val _pgStatus by lazy { MutableLiveData<String>() }
    val pgStatus: LiveData<String> = _pgStatus

    private val _events: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val events: LiveData<String> = _events

    fun changePassword(currentPassword: String, newPassword: String) {
        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE
        compositeDisposable.add(
            editDataInteractor.changePassword(currentPassword, newPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onNotification("Данные изменены")
                    _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                    _events.value = CONSTANTS.ACTION.EVENT_GO_BACK
                },
                    { error ->
                        _pgStatus.value = CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE
                        onError(error)
                    })
        )
    }

}
