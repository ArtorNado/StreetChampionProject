package com.example.streetchampionproject.singleMatch.presentation.participantList.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.streetchampionproject.api.scs.models.Participants

class ParticipantsListAdapter(
    private var participants: List<Participants>,
    private var clickLambda: (Participants) -> Unit
) : RecyclerView.Adapter<ParticipantsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantsListViewHolder =
        ParticipantsListViewHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = participants.size

    override fun onBindViewHolder(holder: ParticipantsListViewHolder, position: Int) {
        holder.bind(participants[position])
    }
}
