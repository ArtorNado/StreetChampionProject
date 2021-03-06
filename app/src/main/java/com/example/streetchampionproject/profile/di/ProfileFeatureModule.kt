package com.example.streetchampionproject.profile.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.api.apiFactory.ApiFactory
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import com.example.streetchampionproject.profile.data.ProfileRepositoryImpl
import com.example.streetchampionproject.profile.data.interfaces.ProfileRepository
import com.example.streetchampionproject.profile.di.scope.ProfileFragmentScope
import com.example.streetchampionproject.profile.domain.ProfileInteractorImpl
import com.example.streetchampionproject.profile.domain.interfaces.ProfileInteractor
import com.example.streetchampionproject.profile.presentation.ProfileViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ProfileFeatureModule {

    @ProfileFragmentScope
    @Provides
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun provideProfileViewModel(profileInteractor: ProfileInteractor, userId: Int): ViewModel {
        return ProfileViewModel(
            profileInteractor,
            userId
        )
    }

    @ProfileFragmentScope
    @Provides
    fun provideViewModelCreator(
        fragment: Fragment,
        viewModelFactory: ViewModelProvider.Factory
    ): ProfileViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(ProfileViewModel::class.java)


    @ProfileFragmentScope
    @Provides
    fun provideMainInteractor(profileInteractor: ProfileInteractorImpl): ProfileInteractor =
        profileInteractor

    @ProfileFragmentScope
    @Provides
    fun provideMainRepository(profileRepository: ProfileRepositoryImpl)
            : ProfileRepository = profileRepository

    @ProfileFragmentScope
    @Provides
    fun provideService(apiFactory: ApiFactory) = apiFactory.profileService
}
