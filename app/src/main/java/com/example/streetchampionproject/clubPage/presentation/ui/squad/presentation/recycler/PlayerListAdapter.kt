package com.example.streetchampionproject.clubPage.presentation.ui.squad.presentation.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.streetchampionproject.api.scs.response.Players

class PlayerListAdapter(
    private var players: List<Players>,
    private var clickLambda: (Players) -> Unit
) : RecyclerView.Adapter<PlayerListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListViewHolder =
        PlayerListViewHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayerListViewHolder, position: Int) {
        holder.bind(players[position])
    }
}
