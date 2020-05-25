package com.example.streetchampionproject.notification.presentation.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.streetchampionproject.api.scs.models.Notification

class NotificationListAdapter(
    private var notifications: List<Notification>,
    private var clickLambda: (Notification) -> Unit
) : ListAdapter<Notification, NotificationListViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationListViewHolder =
        NotificationListViewHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = notifications.size

    override fun onBindViewHolder(holder: NotificationListViewHolder, position: Int) {
        holder.bind(notifications[position])
    }

    fun updateList(newList: List<Notification>) {
        DiffUtil.calculateDiff(
            com.example.streetchampionproject.common.presentation.diff.DiffUtil(
                this.notifications,
                newList
            ), false)
            .dispatchUpdatesTo(this)
        this.notifications = newList
    }
}
