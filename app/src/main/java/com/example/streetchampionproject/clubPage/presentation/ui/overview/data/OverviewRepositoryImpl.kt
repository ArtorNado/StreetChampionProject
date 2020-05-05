package com.example.streetchampionproject.clubPage.presentation.ui.overview.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.clubPage.presentation.ui.overview.data.interfaces.OverviewRepository
import javax.inject.Inject

class OverviewRepositoryImpl @Inject constructor(
    private val streetChampionService: StreetChampionService
): OverviewRepository {
    //some code

}
