package com.example.streetchampionproject.singleMatch.presentation.participantList.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.Participants
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.player_template.view.*

class ParticipantsListViewHolder(
    override val containerView: View,
    private val clickLambda: (Participants) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(participants: Participants) {
        containerView.tv_firstName.text = participants.firstName
        containerView.tv_secondName.text = participants.secondName
        containerView.tv_city.text = participants.userCity
        itemView.setOnClickListener {
            clickLambda(participants)
        }
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (Participants) -> Unit) =
            ParticipantsListViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.player_template,
                    parent,
                    false
                ),
                clickLambda
            )
    }
}
