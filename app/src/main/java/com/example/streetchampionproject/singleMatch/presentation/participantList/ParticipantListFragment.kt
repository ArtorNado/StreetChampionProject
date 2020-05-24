package com.example.streetchampionproject.singleMatch.presentation.participantList

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
import com.example.streetchampionproject.api.scs.models.Participants
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.singleMatch.presentation.participantList.recycler.ParticipantsListAdapter
import kotlinx.android.synthetic.main.participant_list_fragment.*
import javax.inject.Inject

class ParticipantListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: ParticipantListViewModel? = null

    private var matchId: Int? = null
    private var bundle: Bundle? = null
    private var adapter: ParticipantsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.participant_list_fragment, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bundle = this.arguments
        matchId = bundle?.getInt("matchId")
        Injector.plusParticipantListFeatureComponent(matchId?: 0).inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.updateParticipants()
        initObservers()
    }

    private fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(ParticipantListViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    private fun setAdapter(list: List<Participants>) {
        rv_participants_list.layoutManager = LinearLayoutManager(context)
        adapter = ParticipantsListAdapter(list) { players ->
        }
        rv_participants_list.adapter = adapter
    }

    private fun initObservers() {
        viewModel?.participants?.observe(viewLifecycleOwner, Observer {
            setAdapter(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearParticipantListFeatureComponent()
    }

    companion object {
        fun newInstance(matchId: Int): ParticipantListFragment {
            val participantListFragment = ParticipantListFragment()
            val args = Bundle()
            args.putInt("matchId", matchId)
            participantListFragment.arguments = args
            return participantListFragment
        }
    }

}
