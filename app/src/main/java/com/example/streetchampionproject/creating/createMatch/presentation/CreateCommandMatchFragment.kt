package com.example.streetchampionproject.creating.createMatch.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.CreateCommandMatch
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.common.presentation.BaseFragment
import kotlinx.android.synthetic.main.create_command_match_fragment.*

class CreateCommandMatchFragment : BaseFragment<CreateMatchViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.create_command_match_fragment, container, false)

    override fun inject() {
        Injector.plusCreateMatchFeatureComponent(this).inject(this)
    }

    override fun subscribe(viewModel: CreateMatchViewModel) {
        viewModel.determineUserStatus()
        observe(viewModel.event, Observer {
            if (it == "Go back") findNavController().popBackStack()
        })
    }

    override fun initClickListeners() {
        btn_create.setOnClickListener {
            viewModel.createCommandMatch(
                CreateCommandMatch(
                    et_date.text.toString(),
                    et_time.text.toString(),
                    0,
                    et_match_city.text.toString(),
                    et_description.text.toString()
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearCreateMatchFeatureComponent()
    }

}
