package com.example.streetchampionproject.clubPage.presentation.ui.overview.presentation.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.streetchampionproject.api.scs.models.EndedCommandMatch
import com.example.streetchampionproject.api.scs.models.MatchCommand

class ClubMatchesAdapter(
    private val dataSet: List<Any?>,
    private val clickLambda: (Any?) -> Unit
) : RecyclerView.Adapter<ClubMatchesViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubMatchesViewHolder<*> {
        return when (viewType) {
            TYPE_ENDED -> EndedClubMatchesViewHolder.create(parent, clickLambda)
            TYPE_FEATURE -> FeatureClubMatchesViewHolder.create(parent, clickLambda)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ClubMatchesViewHolder<*>, position: Int) {
        when (holder) {
            is EndedClubMatchesViewHolder -> holder.bind(dataSet[position] as EndedCommandMatch)
            is FeatureClubMatchesViewHolder -> holder.bind(dataSet[position] as MatchCommand)
            else -> throw IllegalArgumentException("invalid holder type")
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (dataSet[position]) {
            is EndedCommandMatch -> TYPE_ENDED
            is MatchCommand -> TYPE_FEATURE
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    companion object {
        private const val TYPE_ENDED = 0
        private const val TYPE_FEATURE = 1
    }
}
