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
import com.example.streetchampionproject.common.presentation.CONSTANTS
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
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
            if (it == CONSTANTS.ACTION.EVENT_GO_MAIN) navigator.openMain(
                this,
                viewModel?.userId ?: 0
            )
        })
        viewModel?.pgStatus?.observe(this, Observer {
            pg_logIn.visibility = when (it) {
                CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE -> View.VISIBLE
                CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE -> View.GONE
                else -> View.GONE
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
