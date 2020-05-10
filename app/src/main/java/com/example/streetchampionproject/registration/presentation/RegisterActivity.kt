package com.example.streetchampionproject.registration.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.R
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.registration.data.model.User
import kotlinx.android.synthetic.main.registration.*
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: RegisterViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)
        Injector.plusRegiserFeatureComponent().inject(this)
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
            viewModel?.clickRegister(
                User(
                    et_login.text.toString(), et_password.text.toString(),
                    et_firstName.text.toString(), et_secondName.text.toString(),
                    filled_gender.editableText.toString(), et_city.text.toString()
                ),
                this
            )
        }
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
