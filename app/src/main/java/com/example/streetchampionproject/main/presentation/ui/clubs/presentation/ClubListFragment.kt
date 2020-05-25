package com.example.streetchampionproject.main.presentation.ui.clubs.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.main.presentation.ui.clubs.presentation.recycler.ClubListAdapter
import kotlinx.android.synthetic.main.fragment_club_list.*
import javax.inject.Inject

class ClubListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: ClubListViewModel? = null

    private var adapter: ClubListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_club_list, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusClubListFeatureComponent().inject(this)
        initViewModel()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        initObsrvers()
    }

    private fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(ClubListViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    private fun initObsrvers() {
        viewModel?.pgStatus?.observe(viewLifecycleOwner, Observer {
            progress_bar.visibility = it
        })
        viewModel?.clubList?.observe(viewLifecycleOwner, Observer {
            if(adapter == null) setAdapter(it)
            else adapter?.updateList(it)
        })
        viewModel?.searchError?.observe(viewLifecycleOwner, Observer {
            tf_city.error = it.toString()
        })
    }

    private fun setAdapter(list: List<Teams>) {
        rv_club_list.layoutManager = LinearLayoutManager(context)
        adapter = ClubListAdapter(list) {
            val bundle = Bundle()
            bundle.putInt("teamId", it.teamId)
            findNavController().navigate(R.id.action_navigation_notifications_to_clubPageFragment, bundle)
        }
        rv_club_list.adapter = adapter
    }

    private fun initClickListeners() {
        btn_apply.setOnClickListener {
            viewModel?.getTeamsByCity(et_city.text.toString())
        }
        btn_apply.setOnClickListener {
            applyButtonClick()
        }
    }

    private fun applyButtonClick() {
        viewModel?.getTeamsByCity(et_city.text.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearClubListFeatureComponent()
    }

}
