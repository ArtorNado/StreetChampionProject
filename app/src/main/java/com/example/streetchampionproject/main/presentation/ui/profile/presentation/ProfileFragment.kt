package com.example.streetchampionproject.main.presentation.ui.profile.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.streetchampionproject.R
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.main.presentation.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: ProfileViewModel? = null

    private var teamId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusProfileFeatureComponent(activity?.intent?.extras?.getInt("id") ?: 0)
            .inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initClickListeners()
    }

    private fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(ProfileViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    private fun initClickListeners(){
        tv_club_name.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("teamId", teamId?:0)
            findNavController().navigate(R.id.action_navigation_home_to_clubPageFragment, bundle)
        }
    }

    fun initObservers() {
        viewModel?.user?.observe(viewLifecycleOwner, Observer {
            teamId = it.teamId
            with(it) {
                tv_user_login.text = userSecondName
                tv_user_firstName.text = userFirstName
                tv_user_secondName.text = userSecondName
                tv_user_gender.text = userGender
                tv_city_name.text = userCity
                tv_club_name.text = teamName
            }
        })
        viewModel?.error?.observe(viewLifecycleOwner, Observer {
            Snackbar.make(
                (activity as MainActivity).findViewById(android.R.id.content),
                it,
                Snackbar.LENGTH_SHORT
            ).show()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearProfileFeatureComponent()
    }
}
