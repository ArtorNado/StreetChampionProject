package com.example.streetchampionproject.clubPage.data

import com.example.streetchampionproject.clubPage.data.interfaces.ClubPageRepository
import javax.inject.Inject

class ClubPageRepositoryImpl @Inject constructor(
    private var clubPageRepository: ClubPageRepository
): ClubPageRepository {
}
