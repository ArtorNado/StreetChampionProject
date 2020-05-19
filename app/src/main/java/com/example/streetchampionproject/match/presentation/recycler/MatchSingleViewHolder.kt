package com.example.streetchampionproject.match.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.MatchSingle
import kotlinx.android.synthetic.main.single_match_template.view.*

class MatchSingleViewHolder(
    override val containerView: View,
    private val clickLambda: (MatchSingle) -> Unit
) : MatchListViewHolder<MatchSingle>(containerView) {


    override fun bind(match: MatchSingle) {
        containerView.tv_date.text = match.date
        containerView.tv_current_players.text =
            match.currentNumberParticipant.toString() + "/" + match.numberParticipant.toString()
        containerView.tv_city.text = match.matchCity
        itemView.setOnClickListener {
            clickLambda(match)
        }
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (MatchSingle) -> Unit) =
            MatchSingleViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.single_match_template,
                    parent,
                    false
                ),
                clickLambda
            )
    }
}

