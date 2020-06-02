package com.example.streetchampionproject.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.streetchampionproject.R
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.app.navigation.Navigator
import com.example.streetchampionproject.main.presentation.MainActivity
import kotlinx.android.synthetic.main.settings_fragment.*
import javax.inject.Inject

class SettingsFragment : Fragment() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.settings_fragment, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusSettingsFeatureComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    private fun initClickListeners() {
        val navController = findNavController()
        btn_edit_profile_data.setOnClickListener {
            navController.navigate(R.id.action_navigation_settings_to_editProfileDataFragment)
        }
        btn_change_password.setOnClickListener {
            navController.navigate(R.id.action_navigation_settings_to_changePasswordFragment)
        }
        btn_log_out.setOnClickListener {
            navigator.openLogin(activity as MainActivity)
        }
    }

}
