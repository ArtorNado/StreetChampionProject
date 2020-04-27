package com.example.streetchampionproject.main.presentation.ui.clubs.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.response.Teams
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.team_template.view.*

class ClubListViewHolder(
    override val containerView: View,
    private val clickLambda: (Teams) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(teams: Teams) {
/*
        Log.e("NAME", teams.teamName)
*/
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
                    R.layout.team_template,
                    parent,
                    false
                ),
                clickLambda
            )
    }
}
