package com.example.streetchampionproject.loginActivity.presentation.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.loginActivity.domain.LoginInteractor
import javax.inject.Inject

class LoginViewModel(
    private val loginInteractor: LoginInteractor,
    private val navigator: Navigator
) : ViewModel() {

  /*  init {
        service.citiesInCicle(latitude, longitude, Constants.NEARCITY.CITY_COUNT)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result -> cityList.value = result.list }
    }
*/
    fun clickLogin(email: String, password:String){
        loginInteractor.logIn(email, password)
    }

    fun clickRegistr(context: Context){
        navigator.openRegister(context)
    }
}
