package com.example.streetchampionproject.registration.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.R
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.common.presentation.CONSTANTS
import com.example.streetchampionproject.registration.data.model.User
import kotlinx.android.synthetic.main.registration.*
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigator: Navigator

    private var viewModel: RegisterViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)
        Injector.plusRegiserFeatureComponent().inject(this)
        initViewModel()
        initObservers()
        initClickListeners()
        initComponents()
    }

    private fun initClickListeners() {
        clickRegistr()
    }

    private fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(RegisterViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    private fun clickRegistr() {
        btn_register.setOnClickListener {
            viewModel?.clickRegister(
                User(
                    et_login.text.toString(), et_password.text.toString(),
                    et_firstName.text.toString(), et_secondName.text.toString(),
                    filled_gender.editableText.toString(), et_city.text.toString()
                )
            )
        }
    }

    private fun initObservers() {
        viewModel?.pgStatus?.observe(this, Observer {
            progress_bar.visibility = when (it) {
                CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE -> View.VISIBLE
                CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE-> View.GONE
                else -> View.GONE
            }
        })
        viewModel?.goTo?.observe(this, Observer {
            if (it == "Go to main") navigator.openMain(this, viewModel?.userId ?: 0)
        })
    }

    private fun initComponents() {
        createGenderSelection()
    }

    private fun createGenderSelection() {
        val gender =
            arrayOf<String?>("Man", "Female")
        val adapter = ArrayAdapter<Any?>(
            this,
            R.layout.dropdown_menu_popup_item,
            gender
        )
        filled_gender.setAdapter(adapter)
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearRegisterFeatureComponent()
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }

}
