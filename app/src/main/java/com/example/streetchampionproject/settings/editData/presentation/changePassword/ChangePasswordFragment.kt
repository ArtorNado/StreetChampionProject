package com.example.streetchampionproject.settings.editData.presentation.changePassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.streetchampionproject.R
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.common.presentation.BaseFragment
import com.example.streetchampionproject.common.presentation.CONSTANTS
import kotlinx.android.synthetic.main.change_password_fragment.*

class ChangePasswordFragment : BaseFragment<ChangePasswordViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.change_password_fragment, container, false)

    override fun inject() {
        Injector.plusEditDataFeatureComponent(this).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(view)
    }

    override fun initClickListeners() {
        et_confirm_password.addTextChangedListener {
            if (et_confirm_password.text.toString() == et_new_password.text.toString()) {
                if (tf_confirm_password.isErrorEnabled) tf_confirm_password.error = null
            } else tf_confirm_password.error = "Пароли не совпадают"
        }
        btn_change.setOnClickListener {
            if (tf_confirm_password.error == null && et_current_password.text.toString().isNotEmpty()) {
                viewModel.changePassword(
                    et_current_password.text.toString(),
                    et_confirm_password.text.toString()
                )
            } else snackBar("Проверьте данные")
        }
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun subscribe(viewModel: ChangePasswordViewModel) {
        observe(viewModel.pgStatus, Observer {
            progress_bar.visibility = when (it) {
                CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE -> View.VISIBLE
                CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE -> View.GONE
                else -> View.GONE
            }
        })
        observe(viewModel.events, Observer {
            when (it) {
                CONSTANTS.ACTION.EVENT_GO_BACK ->
                    findNavController().popBackStack()
            }
        })
    }

    private fun initToolbar(view: View) {
        toolbar.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_arrow_back_light24dp)
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearEditDataFeatureComponent()
    }

}
