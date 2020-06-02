package com.example.streetchampionproject.settings.editData.presentation.editProfileData

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.streetchampionproject.R
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.common.presentation.BaseFragment
import com.example.streetchampionproject.common.presentation.CONSTANTS
import kotlinx.android.synthetic.main.edit_profile_data_fragment.*

class EditProfileDataFragment : BaseFragment<EditProfileDataViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.edit_profile_data_fragment, container, false)

    override fun inject() {
        Injector.plusEditDataFeatureComponent(this).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(view)
    }

    override fun initClickListeners() {
        btn_edit.setOnClickListener {
            viewModel.editData(
                et_firstName.text.toString() ?: "",
                et_secondName.text.toString() ?: "",
                et_city.text.toString() ?: ""
            )
        }
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun subscribe(viewModelProfile: EditProfileDataViewModel) {
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
