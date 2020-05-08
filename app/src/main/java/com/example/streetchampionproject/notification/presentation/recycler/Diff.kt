package com.example.streetchampionproject.notification.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.streetchampionproject.api.scs.response.Notification

object Diff : DiffUtil.ItemCallback<Notification>() {

    override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean =
        oldItem.notificationId == newItem.notificationId

    override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean =
        oldItem.senderId == newItem.senderId

}
