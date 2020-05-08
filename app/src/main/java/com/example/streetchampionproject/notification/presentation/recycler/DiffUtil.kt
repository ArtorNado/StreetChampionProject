package com.example.streetchampionproject.notification.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.streetchampionproject.api.scs.response.Notification

class DiffUtil(private val oldList: List<Notification>, private val newList: List<Notification>) :
DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}
