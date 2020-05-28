package com.example.streetchampionproject.clubPage.presentation.ui.overview.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.MatchCommand
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.clubPage.presentation.ui.overview.presentation.recycler.ClubMatchesAdapter
import com.example.streetchampionproject.common.presentation.BaseFragment
import kotlinx.android.synthetic.main.overview_fragment.*

class OverviewFragment : BaseFragment<OverviewViewModel>() {

    private var adapter: ClubMatchesAdapter? = null
    private var teamId: Int? = null
    private var bundle: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.overview_fragment, container, false)

    override fun onResume() {
        super.onResume()
        ch_group_match_type.clearCheck()
    }

    override fun inject() {
        bundle = this.arguments
        teamId = bundle?.getInt("teamId")
        Injector.plusOverviewFeatureComponent(teamId ?: 0, this).inject(this)
    }

    override fun subscribe(viewModel: OverviewViewModel) {
        observe(viewModel.matchList, Observer {
            setAdapter(it)
        })
    }

    override fun initClickListeners() {
        ch_group_match_type.setOnCheckedChangeListener { group, checkedId ->
            viewModel.getData(checkedId)
        }
    }

    private fun setAdapter(list: List<Any?>) {
        rv_match_list.layoutManager = LinearLayoutManager(context)
        adapter = ClubMatchesAdapter(list) { match ->
            when (match) {
                is MatchCommand -> {
                    bundle?.putInt("matchId", match.matchId)
                    view?.findNavController()
                        ?.navigate(R.id.action_clubPageFragment_to_commandMatchFragment, bundle)
                }
            }
        }
        rv_match_list.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearOverviewFeatureComponent()
    }

    companion object {
        fun newInstance(teamId: Int): OverviewFragment {
            val overviewFragment = OverviewFragment()
            val args = Bundle()
            args.putInt("teamId", teamId)
            overviewFragment.arguments = args
            return overviewFragment
        }
    }

}
