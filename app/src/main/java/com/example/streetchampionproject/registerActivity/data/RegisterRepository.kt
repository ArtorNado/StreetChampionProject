package com.example.streetchampionproject.registerActivity.data

import android.util.Log
import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.registerActivity.data.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegisterRepository(private var streetChampionService: StreetChampionService) {

    fun registration(u: User) {
        streetChampionService.registration(u)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> Log.e("RESULT", result.toString()) },
                { error -> Log.e("ERROR", error.toString()) })
    }

}
