package com.example.streetchampionproject.clubPage.presentation.ui

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.streetchampionproject.clubPage.presentation.ui.overview.presentation.OverviewFragment
import com.example.streetchampionproject.clubPage.presentation.ui.squad.presentation.SquadFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val teamId: Int) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int) = when (position) {
        0 -> OverviewFragment.newInstance(teamId)
        1 -> SquadFragment.newInstance(teamId)
        else -> SquadFragment.newInstance(teamId)
    }

    override fun getItemCount(): Int = CARD_ITEM_SIZE

    companion object {
        private const val CARD_ITEM_SIZE = 2
    }
}
