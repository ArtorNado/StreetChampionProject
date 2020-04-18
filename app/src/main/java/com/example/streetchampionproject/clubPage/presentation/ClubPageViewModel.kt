package com.example.streetchampionproject.clubPage.presentation

import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.clubPage.domain.interfaces.ClubPageInteractor

class ClubPageViewModel(
    private val clubPageInteractor: ClubPageInteractor
) : ViewModel()
