package com.example.streetchampionproject.profile.presentation

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
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : BaseFragment<ProfileViewModel>() {

    private var teamId: Int? = null
    private var bundle: Bundle? = null
    private var userId: Int? = null
    private var tb = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.profile_fragment, container, false)


    override fun inject() {
        bundle = this.arguments
        if (bundle?.getInt("id") == null) userId = activity?.intent?.extras?.getInt("id") ?: 0
        else {
            userId = bundle?.getInt("id")
            tb = true
        }
        Injector.plusProfileFeatureComponent(userId ?: 0, this)
            .inject(this)
        bundle?.remove("id")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (tb) initToolbar(view)
    }

    override fun initClickListeners() {
        tv_club_name.setOnClickListener {
            if (teamId != 0) {
                val bundle = Bundle()
                bundle.putInt("teamId", teamId ?: 0)
                findNavController().navigate(
                    R.id.action_navigation_home_to_clubPageFragment,
                    bundle
                )
            }
        }
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun subscribe(viewModel: ProfileViewModel) {
        observe(viewModel.user, Observer {
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
        observe(viewModel.pgStatus, Observer {
            progress_bar.visibility = when (it) {
                CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE -> View.VISIBLE
                CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE -> View.GONE
                else -> View.GONE
            }
        })
    }

    private fun initToolbar(view: View) {
        toolbar.visibility = View.VISIBLE
        toolbar.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_arrow_back_light24dp)
    }

    override fun onStop() {
        super.onStop()
        Injector.clearProfileFeatureComponent()
    }

}
