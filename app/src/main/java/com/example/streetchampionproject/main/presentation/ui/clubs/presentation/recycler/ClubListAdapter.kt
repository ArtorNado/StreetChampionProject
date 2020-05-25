package com.example.streetchampionproject.main.presentation.ui.clubs.presentation.recycler

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.streetchampionproject.api.scs.models.Teams

class ClubListAdapter(
    private var teams: List<Teams>,
    private var clickLambda: (Teams) -> Unit
) : ListAdapter<Teams, ClubListViewHolder>(Diff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubListViewHolder =
        ClubListViewHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: ClubListViewHolder, position: Int) {
        holder.bind(teams[position])
    }

    fun updateList(newList: List<Teams>) {
        Log.e("UPDATE", "UPDATE")
        DiffUtil.calculateDiff(
            com.example.streetchampionproject.common.presentation.diff.DiffUtil(
                this.teams,
                newList
            ), false
        )
            .dispatchUpdatesTo(this)
        this.teams = newList
    }
}
