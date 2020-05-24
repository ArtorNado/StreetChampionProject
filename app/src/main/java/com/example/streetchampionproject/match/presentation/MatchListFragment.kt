package com.example.streetchampionproject.match.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.MatchCommand
import com.example.streetchampionproject.api.scs.models.MatchSingle
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.main.presentation.MainActivity
import com.example.streetchampionproject.match.presentation.recycler.MatchListAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.match_list_fragment.*
import javax.inject.Inject

class MatchListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: MatchListViewModel? = null

    private var adapter: MatchListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.match_list_fragment, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusMatchListFeatureComponent().inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_match_list.layoutManager = LinearLayoutManager(context)
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        ch_group_match_type.clearCheck()
        ch_group_status.clearCheck()
        initClickListeners()
    }

    private fun initClickListeners() {
        ch_group_status.setOnCheckedChangeListener { group, checkedId ->
            pg.visibility = View.VISIBLE
            viewModel?.getData(ch_group_match_type.checkedChipId, checkedId)
        }
        ch_group_match_type.setOnCheckedChangeListener { group, checkedId ->
            pg.visibility = View.VISIBLE
            viewModel?.getData(checkedId, ch_group_status.checkedChipId)
        }
    }

    private fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(MatchListViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    private fun snackBar(text: String) {
        Snackbar.make(
            (activity as MainActivity).findViewById(android.R.id.content),
            text,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun initObservers() {
        viewModel?.matchList?.observe(viewLifecycleOwner, Observer {
            pg.visibility = View.GONE
            setAdapter(it)
        })
        viewModel?.error?.observe(viewLifecycleOwner, Observer {
            pg.visibility = View.GONE
            snackBar(it)
        })
        viewModel?.status?.observe(viewLifecycleOwner, Observer {
            pg.visibility = it
        })
    }

    private fun setAdapter(list: List<Any?>) {
        adapter = MatchListAdapter(list) { match ->
            val bundle = Bundle()
            when(match){
                is MatchSingle -> {
                    bundle.putInt("matchId", match.matchId ?: 0)
                    view?.findNavController()?.navigate(R.id.action_navigation_match_to_singleMatchFragment, bundle)
                }
                is MatchCommand -> {
                    bundle.putInt("matchId", match.matchId ?: 0)
                    view?.findNavController()?.navigate(R.id.action_navigation_match_to_commandMatchFragment, bundle)
                }
            }
        }
        rv_match_list.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearMatchListFeatureComponent()
    }


}
