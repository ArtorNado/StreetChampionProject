package com.example.streetchampionproject.login.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.R
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.common.domain.sharedPreference.LocalStorage
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.sign_in.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var localStorage: LocalStorage

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)
        Injector.plusLoginFeatureComponent().inject(this)
        initViewModel()
        initOnClickListeners()
        initObservers()
    }

    private fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(LoginViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    private fun initOnClickListeners() {
        btn_register.setOnClickListener {
            navigator.openRegister(this)
        }
        btn_logIn.setOnClickListener {
            viewModel?.clickLogin(et_login.text.toString(), et_password.text.toString())
        }
    }

    fun initObservers() {
        viewModel?.goTo?.observe(this, Observer {
            if (it == "Go to main") navigator.openMain(this, viewModel?.userId ?: 0)
        })
        viewModel?.pgStatus?.observe(this, Observer {
            when (it) {
                "Visible" -> pg_logIn.visibility = View.VISIBLE
                "Gone" -> pg_logIn.visibility = View.GONE
            }
        })
        viewModel?.error?.observe(this, Observer {
            Snackbar.make(
                findViewById(android.R.id.content),
                getString(it),
                Snackbar.LENGTH_SHORT
            ).show()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearLoginFeatureComponent()
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }


}
