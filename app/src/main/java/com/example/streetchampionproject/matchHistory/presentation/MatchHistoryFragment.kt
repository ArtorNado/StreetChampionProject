package com.example.streetchampionproject.matchHistory.presentation

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
import com.example.streetchampionproject.common.presentation.BaseFragment
import com.example.streetchampionproject.matchHistory.presentation.recycler.ClubMatchesAdapter
import kotlinx.android.synthetic.main.match_history_fragment.*

class MatchHistoryFragment : BaseFragment<MatchHistoryViewModel>() {

    private var adapter: ClubMatchesAdapter? = null
    private var teamId: Int? = null
    private var bundle: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.match_history_fragment, container, false)

    override fun onResume() {
        super.onResume()
        ch_group_match_type.clearCheck()
    }

    override fun inject() {
        bundle = this.arguments
        teamId = bundle?.getInt("teamId")
        Injector.plusMatchHistoryFeatureComponent(teamId ?: 0, this).inject(this)
    }

    override fun subscribe(viewModel: MatchHistoryViewModel) {
        observe(viewModel.matchList, Observer {
            setAdapter(it)
        })
    }

    override fun initClickListeners() {
        ch_group_match_type.setOnCheckedChangeListener { _, checkedId ->
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
        Injector.clearMatchHistoryFeatureComponent()
    }

    companion object {
        fun newInstance(teamId: Int): MatchHistoryFragment {
            val overviewFragment = MatchHistoryFragment()
            val args = Bundle()
            args.putInt("teamId", teamId)
            overviewFragment.arguments = args
            return overviewFragment
        }
    }

}
