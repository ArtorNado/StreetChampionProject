package com.example.streetchampionproject.main.presentation.ui.clubs.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.streetchampionproject.api.scs.models.Teams

object Diff : DiffUtil.ItemCallback<Teams>() {

    override fun areItemsTheSame(oldItem: Teams, newItem: Teams): Boolean =
        oldItem.teamId == newItem.teamId

    override fun areContentsTheSame(oldItem: Teams, newItem: Teams): Boolean =
        oldItem.teamId == newItem.teamId

}
