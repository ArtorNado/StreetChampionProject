package com.example.streetchampionproject.match.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.streetchampionproject.api.scs.models.MatchCommand
import com.example.streetchampionproject.api.scs.models.MatchSingle

object Diff : DiffUtil.ItemCallback<Any?>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is MatchSingle && newItem is MatchSingle) oldItem.matchId == newItem.matchId
        else if (oldItem is MatchCommand && newItem is MatchCommand) oldItem.matchId == newItem.matchId
        else throw IllegalArgumentException("Bad item type")
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is MatchSingle && newItem is MatchSingle) oldItem.matchCity == newItem.matchCity
        else if (oldItem is MatchCommand && newItem is MatchCommand) oldItem.matchCity == newItem.matchCity
        else throw IllegalArgumentException("Bad item type")
    }

}
