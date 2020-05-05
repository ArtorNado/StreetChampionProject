package com.example.streetchampionproject.clubPage.presentation.ui.overview.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.response.Overview
import kotlinx.android.extensions.LayoutContainer

class OverviewViewHolder(
    override val containerView: View,
    private val clickLambda: (Overview) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(overview: Overview) {
        itemView.setOnClickListener{
            clickLambda(overview)
        }
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (Overview) -> Unit) =
            OverviewViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.overview_template,
                    parent,
                    false
                ),
                clickLambda
            )
    }
}
