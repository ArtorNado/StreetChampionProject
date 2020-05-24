package com.example.streetchampionproject.creating.createTeam.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.R
import com.example.streetchampionproject.app.injector.Injector
import kotlinx.android.synthetic.main.fragment_create_team.*
import javax.inject.Inject

class CreateTeamFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: CreateTeamViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_create_team, container, false)

    override fun onAttach(context: Context) {
        Injector.plusCreateTeamFeatureComponent().inject(this)
        super.onAttach(context)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    private fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(CreateTeamViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    private fun initClickListeners() {
        btn_create.setOnClickListener {
            viewModel?.createTeam(et_club_name.text.toString(), et_club_city.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearCreateTeamFeatureComponent()
    }

}
