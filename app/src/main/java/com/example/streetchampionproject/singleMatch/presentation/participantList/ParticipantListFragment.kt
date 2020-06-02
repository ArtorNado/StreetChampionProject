package com.example.streetchampionproject.singleMatch.presentation.participantList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.Participants
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.common.presentation.BaseFragment
import com.example.streetchampionproject.singleMatch.presentation.participantList.recycler.ParticipantsListAdapter
import kotlinx.android.synthetic.main.participant_list_fragment.*

class ParticipantListFragment : BaseFragment<ParticipantListViewModel>() {

    private var matchId: Int? = null
    private var bundle: Bundle? = null
    private var adapter: ParticipantsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.participant_list_fragment, container, false)

    override fun inject() {
        bundle = this.arguments
        matchId = bundle?.getInt("matchId")
        Injector.plusParticipantListFeatureComponent(matchId ?: 0, this).inject(this)
    }

    override fun initClickListeners() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun subscribe(viewModel: ParticipantListViewModel) {
        viewModel.updateParticipants()
        observe(viewModel.participants, Observer {
            setAdapter(it)
        })
    }

    private fun setAdapter(list: List<Participants>) {
        rv_participants_list.layoutManager = LinearLayoutManager(context)
        adapter = ParticipantsListAdapter(list) { player ->
            bundle?.putInt("id", player.userId)
            view?.findNavController()?.navigate(R.id.action_singleMatchFragment_to_navigation_home, bundle)
        }
        rv_participants_list.adapter = adapter
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
