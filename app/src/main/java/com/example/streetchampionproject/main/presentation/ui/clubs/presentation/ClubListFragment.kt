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
import com.example.streetchampionproject.api.scs.response.Teams
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
    ): View?  = inflater.inflate(R.layout.fragment_club_list, container, false)

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        *//*setHasOptionsMenu(true)*//*
    }*/

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.e("create","create")
        inflater.inflate(R.menu.tool_app_bar, menu)
        val mi = menu.findItem(R.id.action_search)
        Log.e("ACTION VIEW", mi.actionView.toString())
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.e("Menu","Menu pressed")
        if(item.itemId == R.id.action_search) Log.e("Search","Search pressed")
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val mSearchMenuItem = menu.findItem(R.id.action_search)
        val searchView =
            mSearchMenuItem.actionView as SearchView
    }*/

    /* private fun toolBarMenuClick(){
     topAppBar.setOnMenuItemClickListener {
         Log.e("CLIC", "CLICK")
         when (it.itemId) {
             R.id.action_search -> {
                 sv.visibility = View.VISIBLE
                 Log.e("Search","Search")
             }
         }
         true
     }
 }*/

    /*    private fun setSearchListener() {
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextChange(s: String): Boolean {
                Log.e("onQueryTextChange", "onQueryTextChange")
                return true
            }

            override fun onQueryTextSubmit(s: String): Boolean {
                Log.e("onQueryTextSubmit", "onQueryTextSubmit")
                return true
            }
        })
    }*/

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusClubListFeatureComponent().inject(this)
        initViewModel()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*
        topAppBar.inflateMenu(R.menu.tool_app_bar)
*/
        initClickListeners()
        rv_club_list.layoutManager = LinearLayoutManager(context)
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
        viewModel?.clubList?.observe(viewLifecycleOwner, Observer {
            setAdapter(it)
        })
        viewModel?.searchError?.observe(viewLifecycleOwner, Observer {
            tf_city.error = it.toString()
        })
    }

    private fun setAdapter(list: List<Teams>) {
        adapter = ClubListAdapter(list) {
            viewModel?.clickOnClub(findNavController(), it.teamId)
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
        /*setSearchListener()
        toolBarMenuClick()*/
    }

    private fun applyButtonClick() {
        viewModel?.getTeamsByCity(et_city.text.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearClubListFeatureComponent()
    }

}
