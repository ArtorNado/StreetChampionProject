package com.example.streetchampionproject.singleMatch.presentation.matchPage

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
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.single_match_fragment.*

class SingleMatchFragment : BaseFragment<SingleMatchViewModel>() {

    private var matchId: Int? = null
    private var bundle: Bundle? = null
    private var description: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.single_match_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(view)
    }

    override fun inject() {
        bundle = this.arguments
        matchId = bundle?.getInt("matchId")
        matchId.let { Injector.plusSingleMatchFeatureComponent(it ?: 0, this).inject(this) }
    }

    override fun subscribe(viewModel: SingleMatchViewModel) {
        viewModel.updateMatchData()
        viewModel.updateUserStatus()
        observe(viewModel.team, Observer {
            description = it.description
            tv_city.text = it.matchCity
            tv_date.text = it.date
            tv_time.text = it.time
            tv_number_participant.text =
                it.currentNumberParticipant.toString() + "/" + it.numberParticipant.toString()
        })
        observe(viewModel.userStatus, Observer {
            when (it) {
                "Admin" -> btn_end.visibility = View.VISIBLE
                "Undefined" -> btn_apply.visibility = View.VISIBLE
                else -> btn_apply.visibility = View.GONE
            }
        })
        observe(viewModel.updateStatus, Observer {
            if (it) initTabLayout(description ?: "")
        })
        observe(viewModel.goTo, Observer {
            when (it) {
                CONSTANTS.ACTION.EVENT_GO_BACK -> findNavController().popBackStack()
            }
        })
        observe(viewModel.pgStatus, Observer {
            progress_bar.visibility = when (it) {
                CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE -> {
                    View.VISIBLE
                }
                CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE -> {
                    View.GONE
                }
                else -> View.GONE
            }
        })
    }

    private fun initToolbar(view: View) {
        toolbar.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_arrow_back_light24dp)
    }

    private fun initTabLayout(description: String) {
        viewpager.adapter =
            ViewPagerAdapter(childFragmentManager, lifecycle, matchId ?: 0, description)
        TabLayoutMediator(tabs, viewpager,
            TabLayoutMediator.TabConfigurationStrategy { tabs, position ->
                when (position) {
                    0 -> {
                        tabs.text = "Description"
                    }
                    1 -> {
                        tabs.text = "Participants"
                    }
                }
            }).attach()
    }

    override fun initClickListeners() {
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        btn_apply.setOnClickListener {
            viewModel.joinInMatch()
        }
        btn_end.setOnClickListener {
            viewModel.endMatch()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearSingleMatchFeatureComponent()
    }
}
