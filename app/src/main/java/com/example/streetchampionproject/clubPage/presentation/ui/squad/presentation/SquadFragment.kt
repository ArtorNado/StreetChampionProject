package com.example.streetchampionproject.clubPage.presentation.ui.squad.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.response.Players
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.clubPage.presentation.ui.squad.presentation.recycler.PlayerListAdapter
import kotlinx.android.synthetic.main.squad_fragment.*
import javax.inject.Inject

class SquadFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: SquadViewModel? = null

    private var adapter: PlayerListAdapter? = null

    companion object {
        fun newInstance() =
            SquadFragment()

        private const val current = 23
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.squad_fragment, container, false)


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusSquadFeatureComponent().inject(this)
        initViewModel()
        viewModel?.getPlayers(current)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_player_list.layoutManager = LinearLayoutManager(context)
        viewModel?.players?.observe(viewLifecycleOwner, Observer {
            setAdapter(it)
        })
    }

    private fun setAdapter(list: List<Players>) {
        adapter = PlayerListAdapter(list) { players ->
            Log.e("CLICKED", "CLICK")
        }
        rv_player_list.adapter = adapter
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

}
