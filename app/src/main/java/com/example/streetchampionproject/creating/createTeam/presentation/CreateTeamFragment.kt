package com.example.streetchampionproject.creating.createTeam.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.streetchampionproject.R
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.common.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_create_team.*

class CreateTeamFragment : BaseFragment<CreateTeamViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_create_team, container, false)

    override fun inject() {
        Injector.plusCreateTeamFeatureComponent(this).inject(this)
    }

    override fun subscribe(viewModel: CreateTeamViewModel) {
        //some code
    }

    override fun initClickListeners() {
        btn_create.setOnClickListener {
            viewModel.createTeam(et_club_name.text.toString(), et_club_city.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearCreateTeamFeatureComponent()
    }

}
