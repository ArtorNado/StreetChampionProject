package com.example.streetchampionproject.creating.createTeam.presentation

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
import kotlinx.android.synthetic.main.create_team_fragment.*

class CreateTeamFragment : BaseFragment<CreateTeamViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.create_team_fragment, container, false)

    override fun inject() {
        Injector.plusCreateTeamFeatureComponent(this).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(view)
    }

    override fun subscribe(viewModel: CreateTeamViewModel) {
        observe(viewModel.goTo, Observer {
            if (it == CONSTANTS.ACTION.EVENT_GO_BACK) findNavController().popBackStack()
        })
        observe(viewModel.status, Observer {
            when (it) {
                CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE -> progress_bar.visibility = View.GONE
                CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE -> progress_bar.visibility = View.VISIBLE
                else -> progress_bar.visibility = View.GONE
            }
        })
    }

    override fun initClickListeners() {
        btn_create.setOnClickListener {
            viewModel.createTeam(et_club_name.text.toString(), et_club_city.text.toString())
        }
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initToolbar(view: View) {
        toolbar.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_arrow_back_light24dp)
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearCreateTeamFeatureComponent()
    }

}
