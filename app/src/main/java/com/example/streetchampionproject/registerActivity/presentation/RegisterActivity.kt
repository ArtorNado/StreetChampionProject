package com.example.streetchampionproject.registerActivity.presentation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.R
import com.example.streetchampionproject.app.App
import com.example.streetchampionproject.registerActivity.data.model.User
import com.example.streetchampionproject.registerActivity.presentation.viewModel.RegisterViewModel
import com.example.streetchampionproject.registerActivity.presentation.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.registration.*
import javax.inject.Inject


class RegisterActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: RegisterViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)
        val registerActivityComponent = App.appComponent.registerActivityComponent()
        registerActivityComponent.injectRegisterActivity(this)
        initViewModel()
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
            viewModel.clickRegister(
                User(
                    et_login.text.toString(), et_password.text.toString(),
                    et_firstName.text.toString(), et_secondName.text.toString(),
                    filled_gender.editableText.toString(), et_city.text.toString()
                )
            )
        }
    }

    private fun initComponents() {
        createGenderSelection()
    }

    private fun createGenderSelection() {
        val GENDER =
            arrayOf<String?>("Man", "Female")
        val adapter = ArrayAdapter<Any?>(
            this,
            R.layout.dropdown_menu_popup_item,
            GENDER
        )
        filled_gender.setAdapter(adapter)
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }

}
