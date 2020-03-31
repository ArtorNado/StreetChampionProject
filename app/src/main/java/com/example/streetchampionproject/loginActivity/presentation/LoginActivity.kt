package com.example.streetchampionproject.loginActivity.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.streetchampionproject.R
import com.example.streetchampionproject.app.App
import com.example.streetchampionproject.loginActivity.presentation.viewModel.LoginViewModel
import com.example.streetchampionproject.loginActivity.presentation.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.sign_in.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)
        val loginFeatureComponent = App.appComponent.loginFeatureComponent()
        loginFeatureComponent.injectLoginActivity(this)
        initViewModel()
        initOnClickListeners()
    }

    private fun initViewModel(){
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(LoginViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    private fun initOnClickListeners(){
        logInClick()
        registrClick()
    }

    private fun registrClick(){
        btn_register.setOnClickListener {
            viewModel.clickRegistr(this)
        }
    }

    private fun logInClick(){
        btn_logIn.setOnClickListener {
            viewModel.clickLogin(et_login.text.toString(), et_password.text.toString())
        }
    }

    companion object{

        fun start(context: Context){
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }


}
