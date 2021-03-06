package com.example.streetchampionproject.matches.presentation

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.MatchCommand
import com.example.streetchampionproject.api.scs.models.MatchSingle
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.common.presentation.BaseFragment
import com.example.streetchampionproject.common.presentation.CONSTANTS
import com.example.streetchampionproject.matches.presentation.recycler.MatchListAdapter
import kotlinx.android.synthetic.main.match_list_fragment.*

class MatchListFragment : BaseFragment<MatchListViewModel>() {

    private var adapter: MatchListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.match_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_match_list.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        chipChanged()
        if (viewModel.chMatchType != null || viewModel.chStatus != null) {
            viewModel.getDataAfterBackState()
        }
        if (adapter != null) setAdapter(viewModel.matchList.value!!)
    }

    override fun inject() {
        Injector.plusMatchListFeatureComponent(this).inject(this)
    }

    override fun subscribe(viewModel: MatchListViewModel) {
        observe(viewModel.matchList, Observer {
            if (adapter == null) setAdapter(it)
            else adapter?.updateList(it)
        })
        observe(viewModel.pgStatus, Observer {
            when (it) {
                CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE -> pg.visibility = View.GONE
                CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE -> pg.visibility = View.VISIBLE
                else -> pg.visibility = View.GONE
            }
        })
    }

    override fun initClickListeners() {
        setSearchListener()
    }

    private fun chipChanged() {
        ch_group_status.setOnCheckedChangeListener { _, checkedId ->
            if (viewModel.backStatus) {
                viewModel.getData(ch_group_match_type.checkedChipId, checkedId)
            }
        }
        ch_group_match_type.setOnCheckedChangeListener { _, checkedId ->
            if (viewModel.backStatus) {
                viewModel.getData(checkedId, ch_group_status.checkedChipId)
            }
        }
    }

    private fun setAdapter(list: List<Any?>) {
        adapter = MatchListAdapter(list) { match ->
            val bundle = Bundle()
            when (match) {
                is MatchSingle -> {
                    bundle.putInt("matchId", match.matchId)
                    view?.findNavController()
                        ?.navigate(R.id.action_navigation_match_to_singleMatchFragment, bundle)
                    pg.visibility = View.GONE
                }
                is MatchCommand -> {
                    bundle.putInt("matchId", match.matchId)
                    view?.findNavController()
                        ?.navigate(R.id.action_navigation_match_to_commandMatchFragment, bundle)
                    pg.visibility = View.GONE
                }
            }
        }
        rv_match_list.adapter = adapter
    }

    private fun setSearchListener() {
        sv.context.resources.getIdentifier("android:id/search_src_text", null, null)
        val searchPlate = sv.findViewById<EditText>(
            sv.context.resources.getIdentifier(
                "android:id/search_src_text",
                null,
                null
            )
        )
        searchPlate.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.getDataByCity(
                        ch_group_match_type.checkedChipId,
                        ch_group_status.checkedChipId,
                        sv.query.toString()
                    )
                    return true
                }
                return true
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.backStatus = false
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearMatchListFeatureComponent()
    }

}
