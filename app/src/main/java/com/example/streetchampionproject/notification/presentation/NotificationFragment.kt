package com.example.streetchampionproject.notification.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.Notification
import com.example.streetchampionproject.app.injector.Injector
import com.example.streetchampionproject.common.presentation.BaseFragment
import com.example.streetchampionproject.common.presentation.CONSTANTS
import com.example.streetchampionproject.notification.presentation.recycler.NotificationListAdapter
import kotlinx.android.synthetic.main.notification_fragment.*

class NotificationFragment : BaseFragment<NotificationViewModel>() {


    private var adapter: NotificationListAdapter? = null
    private var teamId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.notification_fragment, container, false)

    override fun inject() {
        val bundle = this.arguments
        teamId = bundle?.getInt("teamId")
        Injector.plusNotificationFueatureComponent(teamId ?: 0, this).inject(this)
    }

    override fun initClickListeners() {
        val navController = findNavController()
        toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }

    override fun subscribe(viewModel: NotificationViewModel) {
        viewModel.getData()
        viewModel.pgStatus.observe(viewLifecycleOwner, Observer {
            progress_bar.visibility = when (it) {
                CONSTANTS.PROGRESSBAR.ARG_STATUS_GONE -> View.GONE
                CONSTANTS.PROGRESSBAR.ARG_STATUS_VISIBLE -> View.VISIBLE
                else -> View.GONE
            }
        })
        viewModel.notifications.observe(viewLifecycleOwner, Observer {
            if (adapter == null) setAdapter(it)
            else adapter?.updateList(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar(view)
    }

    private fun initToolBar(view: View) {
        toolbar.navigationIcon =
            ContextCompat.getDrawable(view.context, R.drawable.ic_arrow_back_light24dp)
    }

    private fun setAdapter(list: List<Notification>) {
        rv_notif_list.layoutManager = LinearLayoutManager(context)
        adapter = NotificationListAdapter(list) {
            viewModel.notifAnswer(it)
        }
        rv_notif_list.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.clearNotificationFeatureComponent()
    }

}
