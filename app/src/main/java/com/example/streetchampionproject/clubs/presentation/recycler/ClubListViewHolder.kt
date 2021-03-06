package com.example.streetchampionproject.clubs.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.models.Teams
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.template_team.view.*

class ClubListViewHolder(
    override val containerView: View,
    private val clickLambda: (Teams) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(teams: Teams) {
        containerView.tv_teamName.text = teams.teamName
        containerView.tv_city.text = teams.teamCity
        itemView.setOnClickListener{
            clickLambda(teams)
        }
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (Teams) -> Unit) =
            ClubListViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.template_team,
                    parent,
                    false
                ),
                clickLambda
            )
    }
}
