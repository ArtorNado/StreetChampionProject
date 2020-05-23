package com.example.streetchampionproject.singleMatch.presentation.matchPage

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.streetchampionproject.singleMatch.presentation.description.DescriptionFragment
import com.example.streetchampionproject.singleMatch.presentation.participantList.ParticipantListFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val matchId: Int,
    private val description: String
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int) = when (position) {
        0 -> DescriptionFragment.newInstance(description)
        1 -> ParticipantListFragment.newInstance(matchId)
        else -> ParticipantListFragment.newInstance(matchId)
    }

    override fun getItemCount(): Int = CARD_ITEM_SIZE

    companion object {
        private const val CARD_ITEM_SIZE = 2
    }
}
