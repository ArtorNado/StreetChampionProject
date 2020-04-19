package com.example.streetchampionproject.clubPage.presentation.ui.squad.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.response.Players
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.player_template.view.*

class PlayerListViewHolder(
    override val containerView: View,
    private val clickLambda: (Players) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(player: Players) {
        containerView.tv_firstName.text = player.fisrtName
        containerView.tv_secondName.text = player.secondName
        containerView.tv_city.text = player.userCity
        itemView.setOnClickListener{
            clickLambda(player)
        }
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (Players) -> Unit) =
            PlayerListViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.player_template,
                    parent,
                    false
                ),
                clickLambda
            )
    }
}
