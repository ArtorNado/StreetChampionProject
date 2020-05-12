package com.example.streetchampionproject.main.presentation.ui.profile.di

import androidx.lifecycle.ViewModel
import com.example.streetchampionproject.common.viewModel.ViewModelKey
import com.example.streetchampionproject.main.presentation.ui.profile.data.ProfileRepositoryImpl
import com.example.streetchampionproject.main.presentation.ui.profile.data.interfaces.ProfileRepository
import com.example.streetchampionproject.main.presentation.ui.profile.di.scope.ProfileFragmentScope
import com.example.streetchampionproject.main.presentation.ui.profile.domain.ProfileInteractorImpl
import com.example.streetchampionproject.main.presentation.ui.profile.domain.interfaces.ProfileInteractor
import com.example.streetchampionproject.main.presentation.ui.profile.presentation.ProfileViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ProfileFeatureModule {

    @ProfileFragmentScope
    @Provides
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun provideProfileViewModel(profileInteractor: ProfileInteractor): ViewModel {
        return ProfileViewModel(
            profileInteractor
        )
    }


    @ProfileFragmentScope
    @Provides
    fun provideMainInteractor(profileInteractor: ProfileInteractorImpl): ProfileInteractor =
        profileInteractor

    @ProfileFragmentScope
    @Provides
    fun provideMainRepository(profileRepository: ProfileRepositoryImpl)
            : ProfileRepository = profileRepository
}
