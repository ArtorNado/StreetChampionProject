package com.example.streetchampionproject.clubPage.presentation.ui

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.streetchampionproject.clubPage.presentation.ui.squad.presentation.SquadFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int) = when (position) {
        0 -> SquadFragment.newInstance()
        else -> SquadFragment.newInstance()
    }

    override fun getItemCount(): Int = CARD_ITEM_SIZE

    companion object {
        private const val CARD_ITEM_SIZE = 3
    }
}
