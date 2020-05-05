package com.example.streetchampionproject.clubPage.presentation.ui.overview.presentation

import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.clubPage.presentation.ui.overview.domain.interfaces.OverviewInteractor

class OverviewViewModel(
    private val overviewInteractor: OverviewInteractor,
    private val teamId: Int
) : ViewModel() {
    //some code
}
