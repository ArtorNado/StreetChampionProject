package com.example.streetchampionproject.matchHistory.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.EndedCommandMatch
import kotlinx.android.synthetic.main.ended_command_match_template.view.*

class EndedClubMatchesViewHolder (
    override val containerView: View,
    private val clickLambda: (EndedCommandMatch) -> Unit
) : ClubMatchesViewHolder<EndedCommandMatch>(containerView) {


    override fun bind(match: EndedCommandMatch) {
        containerView.tv_date.text = match.date
        containerView.tv_team1.text = match.firstTeamName
        containerView.tv_team2.text = match.secondTeamName
        containerView.tv_team1_score.text = match.goalsFirstTeam.toString()
        containerView.tv_team2_score.text = match.goalsSecondTeam.toString()
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (EndedCommandMatch) -> Unit) =
            EndedClubMatchesViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.ended_command_match_template,
                    parent,
                    false
                ),
                clickLambda
            )
    }
}
