package com.example.streetchampionproject.clubPage.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.streetchampionproject.R
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.clubPage.presentation.ui.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_club_page_test.*
import javax.inject.Inject

class ClubPageFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: ClubPageViewModel? = null

    private var teamId: Int? = null
    private var bundle: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_club_page_test, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bundle = this.arguments
        teamId = bundle?.getInt("teamId")
        teamId?.let { Injector.plusClubPageFeatureComponent(it).inject(this) }
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(view)
        initTabLayout()
        setObservers()
        teamId?.let {
            viewModel?.determineRole(it)
        }
        viewModel?.getData()
        initClickListeners()
    }

    private fun initClickListeners() {
        val navController = findNavController()
        toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
        btn_notifications.setOnClickListener {
            bundle?.putInt("teamId", teamId ?: 0)
            navController.navigate(R.id.action_clubPageFragment_to_notificationFragment, bundle)
        }
        btn_apply.setOnClickListener {
            viewModel?.applyForMembership()
        }
    }

    private fun initToolbar(view: View) {
        toolbar.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_keyboard_backspace)
    }

    private fun initTabLayout() {
        viewpager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle, teamId ?: 0)
        TabLayoutMediator(tabs, viewpager,
            TabLayoutMediator.TabConfigurationStrategy { tabs, position ->
                when (position) {
                    0 -> {
                        tabs.text = "Matchs"
                    }
                    1 -> {
                        tabs.text = "squad"
                    }
                }
            }).attach()
    }

    private fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(ClubPageViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    private fun setObservers() {
        viewModel?.pgStatus?.observe(viewLifecycleOwner, Observer {
            pg_clubPage.visibility = it
        })
        viewModel?.team?.observe(viewLifecycleOwner, Observer {
            tv_club_name.text = it.teamName
            tv_city_name.text = it.teamCity
        })
        viewModel?.userStatus?.observe(viewLifecycleOwner, Observer {
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

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearClubPageFeatureComponent()
    }

    companion object {
        const val USER_STATUS = "USER"
        const val ADMIN_TATUS = "ADMIN"
        const val PARTICIPANT_STATUS = "PARTICIPANT"
        fun newInstance() =
            ClubPageFragment()

    }

}
