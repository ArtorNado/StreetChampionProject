package com.example.streetchampionproject.clubPage.domain

import com.example.streetchampionproject.clubPage.data.interfaces.ClubPageRepository
import com.example.streetchampionproject.clubPage.domain.interfaces.ClubPageInteractor
import javax.inject.Inject

class ClubPageInteractorImpl @Inject constructor(
    private var clubPageRepository: ClubPageRepository
): ClubPageInteractor
