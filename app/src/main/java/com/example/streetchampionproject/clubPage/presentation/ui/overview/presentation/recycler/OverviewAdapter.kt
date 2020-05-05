package com.example.streetchampionproject.clubPage.presentation.ui.overview.presentation.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.streetchampionproject.api.scs.response.Overview

class OverviewAdapter(
    private var overview: List<Overview>,
    private var clickLambda: (Overview) -> Unit
) : RecyclerView.Adapter<OverviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewViewHolder =
        OverviewViewHolder.create(parent, clickLambda)

    override fun getItemCount(): Int = overview.size

    override fun onBindViewHolder(holder: OverviewViewHolder, position: Int) {
        holder.bind(overview[position])
    }
}
