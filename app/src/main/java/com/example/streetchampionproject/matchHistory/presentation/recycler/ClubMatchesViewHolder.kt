package com.example.streetchampionproject.matchHistory.presentation.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class ClubMatchesViewHolder <T>(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    abstract fun bind(match: T)

}
