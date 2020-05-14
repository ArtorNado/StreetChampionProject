package com.example.streetchampionproject.clubPage.presentation.ui.squad.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.Players
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.clubPage.presentation.ui.squad.presentation.recycler.PlayerListAdapter
import kotlinx.android.synthetic.main.fragment_squad.*
import javax.inject.Inject

class SquadFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: SquadViewModel? = null

    private var adapter: PlayerListAdapter? = null

    companion object {
        fun newInstance(teamId: Int): SquadFragment {
            val squadFragment = SquadFragment()
            val args = Bundle()
            args.putInt("teamId", teamId)
            squadFragment.arguments = args
            return squadFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_squad, container, false)


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusSquadFeatureComponent(arguments?.getInt("teamId") ?: 0).inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        viewModel?.updatePlayers()
    }


    private fun setAdapter(list: List<Players>) {
        rv_player_list.layoutManager = LinearLayoutManager(context)
        adapter = PlayerListAdapter(list) { players ->
        }
        rv_player_list.adapter = adapter
    }

    private fun initObservers() {
        viewModel?.players?.observe(viewLifecycleOwner, Observer {
            setAdapter(it)
        })
    }

    private fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(SquadViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearSquadFeatureComponent()
    }

}
