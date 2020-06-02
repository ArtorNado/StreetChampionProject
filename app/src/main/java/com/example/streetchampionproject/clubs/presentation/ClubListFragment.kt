package com.example.streetchampionproject.clubs.presentation

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.Teams
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.clubs.presentation.recycler.ClubListAdapter
import com.example.streetchampionproject.common.presentation.BaseFragment
import com.example.streetchampionproject.common.presentation.CONSTANTS
import kotlinx.android.synthetic.main.club_list_fragment.*

class ClubListFragment : BaseFragment<ClubListViewModel>() {

    private var adapter: ClubListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.club_list_fragment, container, false)

    override fun onResume() {
        super.onResume()
        if(adapter != null) setAdapter(viewModel.clubList.value!!)
    }

    override fun inject() {
        Injector.plusClubListFeatureComponent(this).inject(this)
    }

    override fun subscribe(viewModel: ClubListViewModel) {
        observe(viewModel.pgStatus, Observer {
            progress_bar.visibility = when (it) {
                CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE -> View.VISIBLE
                CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE -> View.GONE
                else -> View.GONE
            }
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
        setSearchListener()
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
                    viewModel.getTeamsByCity(sv.query.toString())
                    return true
                }
                return true
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearClubListFeatureComponent()
    }

}
