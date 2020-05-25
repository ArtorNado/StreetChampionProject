package com.example.streetchampionproject.match.presentation.recycler

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.streetchampionproject.api.scs.models.MatchCommand
import com.example.streetchampionproject.api.scs.models.MatchSingle

class MatchListAdapter(
    private var dataSet: List<Any?>,
    private val clickLambda: (Any?) -> Unit
) : ListAdapter<Any, MatchListViewHolder<*>>(Diff) {

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

    fun updateList(newList: List<Any?>) {
        Log.e("UPDATE", "UPDATE")
        DiffUtil.calculateDiff(
            com.example.streetchampionproject.common.presentation.diff.DiffUtil(
                this.dataSet,
                newList
            ), false)
            .dispatchUpdatesTo(this)
        this.dataSet = newList   }

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
