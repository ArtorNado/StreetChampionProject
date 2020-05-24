package com.example.streetchampionproject.clubPage.presentation.ui.overview.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.MatchCommand
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.clubPage.presentation.ui.overview.presentation.recycler.ClubMatchesAdapter
import com.example.streetchampionproject.main.presentation.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.overview_fragment.*
import javax.inject.Inject

class OverviewFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var viewModel: OverviewViewModel? = null
    private var adapter: ClubMatchesAdapter? = null
    private var teamId: Int? = null
    private var bundle: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.overview_fragment, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bundle = this.arguments
        teamId = bundle?.getInt("teamId")
        Injector.plusOverviewFeatureComponent(teamId?:0).inject(this)
        initViewModel()
    }

    override fun onResume() {
        super.onResume()
        ch_group_match_type.clearCheck()
        initClickListeners()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers(){
        viewModel?.error?.observe(viewLifecycleOwner, Observer {
            snackBar(it)
        })
        viewModel?.matchList?.observe(viewLifecycleOwner, Observer {
            Log.e("OBSERV", it.toString())
            setAdapter(it)
        })
    }

    private fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(OverviewViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    private fun initClickListeners(){
        ch_group_match_type.setOnCheckedChangeListener { group, checkedId ->
            viewModel?.getData(checkedId)
        }
    }

    private fun setAdapter(list: List<Any?>) {
        rv_match_list.layoutManager = LinearLayoutManager(context)
        adapter = ClubMatchesAdapter(list) { match ->
            when(match){
                is MatchCommand ->{
                    bundle?.putInt("matchId", match.matchId)
                    findNavController().navigate(R.id.action_overviewFragment_to_commandMatchFragment)
                }
            }
        }
        rv_match_list.adapter = adapter
    }

    private fun snackBar(text: String) {
        Snackbar.make(
            (activity as MainActivity).findViewById(android.R.id.content),
            text,
            Snackbar.LENGTH_SHORT
        ).show()
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

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearOverviewFeatureComponent()
    }

}
