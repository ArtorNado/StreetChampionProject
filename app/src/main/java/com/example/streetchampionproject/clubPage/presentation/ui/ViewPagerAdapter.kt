package com.example.streetchampionproject.clubPage.presentation.ui

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.streetchampionproject.matchHistory.presentation.MatchHistoryFragment
import com.example.streetchampionproject.teamSquad.presentation.SquadFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val teamId: Int) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int) = when (position) {
        0 -> MatchHistoryFragment.newInstance(teamId)
        1 -> SquadFragment.newInstance(teamId)
        else -> SquadFragment.newInstance(teamId)
    }

    override fun getItemCount(): Int = CARD_ITEM_SIZE

    companion object {
        private const val CARD_ITEM_SIZE = 2
    }
}
