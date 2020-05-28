package com.example.streetchampionproject.clubPage.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.streetchampionproject.R
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.clubPage.presentation.ui.ViewPagerAdapter
import com.example.streetchampionproject.common.presentation.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_club_page.*

class ClubPageFragment : BaseFragment<ClubPageViewModel>() {

    private var teamId: Int? = null
    private var bundle: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_club_page, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(view)
        initTabLayout()
    }

    override fun inject() {
        bundle = this.arguments
        teamId = bundle?.getInt("teamId")
        teamId?.let { Injector.plusClubPageFeatureComponent(it, this).inject(this) }
        viewModel.updateUserStatus()
        viewModel.updateTeam()
    }

    override fun subscribe(viewModel: ClubPageViewModel) {
        observe(viewModel.pgStatus, Observer {
            when(it){
                "visible" -> progress_bar.visibility = View.VISIBLE
                "gone" -> progress_bar.visibility = View.GONE
            }
        })
        observe(viewModel.team, Observer {
            tv_club_name.text = it.teamName
            tv_city_name.text = it.teamCity
        })
        observe(viewModel.userStatus, Observer {
            when (it) {
                USER_STATUS -> btn_apply.visibility = View.VISIBLE
                PARTICIPANT_STATUS -> btn_chat.visibility = View.VISIBLE
                ADMIN_TATUS -> {
                    btn_chat.visibility = View.VISIBLE
                    btn_notifications.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun initClickListeners() {
        val navController = findNavController()
        toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
        btn_notifications.setOnClickListener {
            bundle?.putInt("teamId", teamId ?: 0)
            navController.navigate(R.id.action_clubPageFragment_to_notificationFragment, bundle)
        }
        btn_apply.setOnClickListener {
            viewModel.applyForMembership()
        }
    }

    private fun initToolbar(view: View) {
        toolbar.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_arrow_back_light24dp)
    }

    private fun initTabLayout() {
        viewpager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle, teamId ?: 0)
        Log.e("TEAM_ID_1", teamId.toString())
        TabLayoutMediator(tabs, viewpager,
            TabLayoutMediator.TabConfigurationStrategy { tabs, position ->
                when (position) {
                    0 -> {
                        tabs.text = "Matchs"
                    }
                    1 -> {
                        tabs.text = "Squad"
                    }
                }
            }).attach()
    }


    override fun onDestroy() {
        super.onDestroy()
        Injector.clearClubPageFeatureComponent()
    }

    companion object {
        const val USER_STATUS = "USER"
        const val ADMIN_TATUS = "ADMIN"
        const val PARTICIPANT_STATUS = "PARTICIPANT"
    }

}
