package com.example.streetchampionproject.teamSquad.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.Players
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.common.presentation.BaseFragment
import com.example.streetchampionproject.teamSquad.presentation.recycler.PlayerListAdapter
import kotlinx.android.synthetic.main.squad_fragment.*

class SquadFragment : BaseFragment<SquadViewModel>() {


    private var adapter: PlayerListAdapter? = null

    private var teamId: Int? = null
    private var bundle: Bundle? = null

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
    ): View? = inflater.inflate(R.layout.squad_fragment, container, false)

    private fun setAdapter(list: List<Players>) {
        rv_player_list.layoutManager = LinearLayoutManager(context)
        adapter = PlayerListAdapter(list) { players ->
            bundle?.putInt("id", players.userId)
            view?.findNavController()?.navigate(R.id.action_clubPageFragment_to_navigation_home, bundle)
        }
        rv_player_list.adapter = adapter
    }

    override fun inject() {
        bundle = this.arguments
        teamId = bundle?.getInt("teamId")
        Injector.plusSquadFeatureComponent(teamId?:0, this).inject(this)
    }

    override fun initClickListeners() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun subscribe(viewModel: SquadViewModel) {
        viewModel.updatePlayers()
        observe(viewModel.players, Observer {
            setAdapter(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearSquadFeatureComponent()
    }

}
