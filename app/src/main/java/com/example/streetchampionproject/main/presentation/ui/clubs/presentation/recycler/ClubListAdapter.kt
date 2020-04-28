package com.example.streetchampionproject.main.presentation.ui.clubs.presentation.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.streetchampionproject.api.scs.response.Teams

class ClubListAdapter(
    private var teams: List<Teams>,
    private var clickLambda: (Teams) -> Unit
) : RecyclerView.Adapter<ClubListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubListViewHolder =
        ClubListViewHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: ClubListViewHolder, position: Int) {
        holder.bind(teams[position])
    }
}