package com.example.streetchampionproject.clubPage.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

    companion object {
        fun newInstance() =
            ClubPageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_club_page_test, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val bundle = this.arguments
        teamId = bundle?.getInt("teamId")
        teamId?.let { Injector.plusClubPageFeatureComponent(it).inject(this) }
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewpager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(tabs, viewpager,
            TabLayoutMediator.TabConfigurationStrategy { tabs, position ->
                when (position) {
                    0 -> { tabs.text = "overview"}
                    1 -> { tabs.text = "squad"}
                    2 -> { tabs.text = "three"}
                }
            }).attach()
        setObservers()
        teamId?.let { viewModel?.determineRole(it) }
        viewModel?.getData()
    }

    private fun initViewModel(){
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(ClubPageViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    private fun setObservers(){
        viewModel?.team?.observe(viewLifecycleOwner, Observer {
            tv_club_name.text = it.teamName
            tv_city_name.text = it.teamCity
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearClubPageFeatureComponent()
    }

}
