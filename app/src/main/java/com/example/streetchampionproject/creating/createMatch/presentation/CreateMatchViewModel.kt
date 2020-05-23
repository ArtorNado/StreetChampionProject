package com.example.streetchampionproject.creating.createMatch.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.api.scs.models.CreateCommandMatch
import com.example.streetchampionproject.api.scs.models.CreateSingleMatch
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.creating.createMatch.domain.interfaces.CreateMatchInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CreateMatchViewModel(
    private val createMatchInteractor: CreateMatchInteractor
) : BaseViewModel() {

    private val _role: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val role: LiveData<String> = _role

    fun createCommandMatch(createCommandMatch: CreateCommandMatch){
        compositeDisposable.add(
            createMatchInteractor.createCommandMatch(createCommandMatch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    {
                        Log.e("EXc", "CREATE_CM")
                    })
        )
    }

    fun createSingleMatch(createSingleMatch: CreateSingleMatch){
        compositeDisposable.add(
            createMatchInteractor.createSingleMatch(createSingleMatch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    {
                        Log.e("ERROR", "CREATE_SM")
                    })
        )
    }

    fun determineUserStatus(){
        compositeDisposable.add(
            createMatchInteractor.determineUserRole()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    Log.e("RESULT", result.toString())
                    _role.value = result.status
                },
                    {
                        Log.e("ERROR", "DETERMINE")
                    })
        )
    }

}
