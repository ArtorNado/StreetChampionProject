package com.example.streetchampionproject.matchHistory.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.MatchCommand
import kotlinx.android.synthetic.main.command_match_template.view.*

class FeatureClubMatchesViewHolder (
    override val containerView: View,
    private val clickLambda: (MatchCommand) -> Unit
) : ClubMatchesViewHolder<MatchCommand>(containerView) {


    override fun bind(match: MatchCommand) {
        containerView.tv_date.text = match.date
        containerView.tv_team1.text = match.firstTeamName
        containerView.tv_team2.text = match.secondTeamName
        itemView.setOnClickListener {
            clickLambda(match)
        }
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (MatchCommand) -> Unit) =
            FeatureClubMatchesViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.command_match_template,
                    parent,
                    false
                ),
                clickLambda
            )
    }
}
