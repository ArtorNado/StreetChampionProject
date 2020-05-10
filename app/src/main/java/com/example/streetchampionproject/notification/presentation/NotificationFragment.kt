package com.example.streetchampionproject.notification.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.Notification
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.notification.presentation.recycler.NotificationListAdapter
import kotlinx.android.synthetic.main.fragment_notification.*
import javax.inject.Inject

class NotificationFragment : Fragment() {

    companion object {
        fun newInstance() = NotificationFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: NotificationViewModel? = null

    private var adapter: NotificationListAdapter? = null
    private var teamId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_notification, container, false)


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val bundle = this.arguments
        teamId = bundle?.getInt("teamId")
        Injector.plusNotificationFueatureComponent(teamId ?: 0).inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar(view)
        setObservers()
        initCLickListeners()
        viewModel?.getData()
    }

    private fun initCLickListeners() {
        val navController = findNavController()
        toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }

    private fun initToolBar(view: View) {
        toolbar.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_arrow_back_light24dp)
    }

    private fun initViewModel() {
        val viewModel by lazy {
            ViewModelProvider(
                this,
                viewModelFactory
            ).get(NotificationViewModel::class.java)
        }
        this.viewModel = viewModel
    }

    private fun setObservers() {
        viewModel?.pgStatus?.observe(viewLifecycleOwner, Observer {
            progress_bar.visibility = it
        })
        viewModel?.notifications?.observe(viewLifecycleOwner, Observer {
            if (adapter == null) setAdapter(it)
            else adapter?.updateList(it)
        })
    }

    private fun setAdapter(list: List<Notification>) {
        rv_notif_list.layoutManager = LinearLayoutManager(context)
        adapter = NotificationListAdapter(list) {
            viewModel?.notifAnswer(it)
        }
        rv_notif_list.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearNotificationFeatureComponent()
    }

}
