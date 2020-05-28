package com.example.streetchampionproject.main.presentation.ui.clubs.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.common.presentation.BaseFragment
import com.example.streetchampionproject.main.presentation.ui.clubs.presentation.recycler.ClubListAdapter
import kotlinx.android.synthetic.main.fragment_club_list.*

class ClubListFragment : BaseFragment<ClubListViewModel>() {

    private var adapter: ClubListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_club_list, container, false)

    override fun onResume() {
        super.onResume()
        if(adapter != null) setAdapter(viewModel.clubList.value!!)
    }

    override fun inject() {
        Injector.plusClubListFeatureComponent(this).inject(this)
    }

    override fun subscribe(viewModel: ClubListViewModel) {
        observe(viewModel.pgStatus, Observer {
            progress_bar.visibility = it
        })
        observe(viewModel.searchError, Observer {
            tf_city.error = it.toString()
        })
        observe(viewModel.clubList, Observer {
            if (adapter == null) setAdapter(it)
            else adapter?.updateList(it)
        })
    }

    private fun setAdapter(list: List<Teams>) {
        rv_club_list.layoutManager = LinearLayoutManager(context)
        adapter = ClubListAdapter(list) {
            val bundle = Bundle()
            bundle.putInt("teamId", it.teamId)
            findNavController().navigate(
                R.id.action_navigation_notifications_to_clubPageFragment,
                bundle
            )
        }
        rv_club_list.adapter = adapter
    }

    override fun initClickListeners() {
        btn_apply.setOnClickListener {
            viewModel.getTeamsByCity(et_city.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearClubListFeatureComponent()
    }

}
