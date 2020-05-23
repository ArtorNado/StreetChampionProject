package com.example.streetchampionproject.singleMatch.presentation.matchPage

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.single_match_fragment.*
import javax.inject.Inject

class SingleMatchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: SingleMatchViewModel? = null

    private var matchId: Int? = null
    private var bundle: Bundle? = null

    private var description: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.single_match_fragment, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bundle = this.arguments
        matchId = bundle?.getInt("matchId")
        matchId.let { Injector.plusSingleMatchFeatureComponent(it ?: 0).inject(this) }
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        viewModel?.updateMatchData()
        viewModel?.updateUserStatus()
        initToolbar(view)
        initClickListeners()
    }

    ////////////////////////FIX SET TEXT STYLE
    private fun initObservers() {
        viewModel?.team?.observe(viewLifecycleOwner, Observer {
            description = it.description
            tv_city.text = it.matchCity
            tv_date.text = it.date
            tv_number_participant.text =
                it.numberParticipant.toString() + "/" + it.currentNumberParticipant.toString()
        })
        viewModel?.userStatus?.observe(viewLifecycleOwner, Observer {
            when (it) {
                "Admin" -> btn_end.visibility = View.VISIBLE
                "Undefined" -> btn_apply.visibility = View.VISIBLE
                else -> btn_apply.visibility = View.GONE
            }
        })
        viewModel?.updateStatus?.observe(viewLifecycleOwner, Observer {
            if(it) initTabLayout(description?:"")
        })
    }

    private fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(SingleMatchViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    private fun initToolbar(view: View) {
        toolbar.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_arrow_back_light24dp)
    }

    private fun initTabLayout(description: String) {
        Log.e("INIT_TAB", "INIT_TAB")
        viewpager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle, matchId ?: 0, description)
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

    private fun initClickListeners() {
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        btn_apply.setOnClickListener {
            viewModel?.joinInMatch()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearSingleMatchFeatureComponent()
    }

}
