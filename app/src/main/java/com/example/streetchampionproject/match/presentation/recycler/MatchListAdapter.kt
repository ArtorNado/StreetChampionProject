package com.example.streetchampionproject.match.presentation.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.streetchampionproject.api.scs.models.MatchCommand
import com.example.streetchampionproject.api.scs.models.MatchSingle

class MatchListAdapter(
    private val dataSet: List<Any?>,
    private val clickLambda: (Any?) -> Unit
) : RecyclerView.Adapter<MatchListViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchListViewHolder<*> {
        return when (viewType) {
            TYPE_SINGLE -> MatchSingleViewHolder.create(parent, clickLambda)
            TYPE_COMMAND -> MatchCommandViewHolder.create(parent, clickLambda)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: MatchListViewHolder<*>, position: Int) {
        when (holder) {
            is MatchSingleViewHolder -> holder.bind(dataSet[position] as MatchSingle)
            is MatchCommandViewHolder -> holder.bind(dataSet[position] as MatchCommand)
            else -> throw IllegalArgumentException("invalid holder type")
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (dataSet[position]) {
            is MatchSingle -> TYPE_SINGLE
            is MatchCommand -> TYPE_COMMAND
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    companion object {
        private const val TYPE_SINGLE = 0
        private const val TYPE_COMMAND = 1
    }

}
