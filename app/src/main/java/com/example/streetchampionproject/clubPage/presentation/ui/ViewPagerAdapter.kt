package com.example.streetchampionproject.clubPage.presentation.ui

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.streetchampionproject.clubPage.presentation.ui.overview.presentation.OverviewFragment


class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int) = when (position) {
        0 -> OverviewFragment.newInstance()
        else -> OverviewFragment.newInstance()
    }

    override fun getItemCount(): Int {
        return CARD_ITEM_SIZE
    }

    companion object {
        private const val CARD_ITEM_SIZE = 3
    }
}
