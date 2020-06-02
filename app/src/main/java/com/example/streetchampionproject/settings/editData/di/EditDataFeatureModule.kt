package com.example.streetchampionproject.settings.editData.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.api.apiFactory.ApiFactory
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import com.example.streetchampionproject.settings.editData.data.EditDataRepositoryImpl
import com.example.streetchampionproject.settings.editData.data.interfaces.EditDataRepository
import com.example.streetchampionproject.settings.editData.di.scope.EditDataScope
import com.example.streetchampionproject.settings.editData.domain.EditDataInteractorImpl
import com.example.streetchampionproject.settings.editData.domain.interfaces.EditDataInteractor
import com.example.streetchampionproject.settings.editData.presentation.changePassword.ChangePasswordViewModel
import com.example.streetchampionproject.settings.editData.presentation.editProfileData.EditProfileDataViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class EditDataFeatureModule {

    @EditDataScope
    @Provides
    @IntoMap
    @ViewModelKey(EditProfileDataViewModel::class)
    fun provideEditDataViewModel(editDataInteractor: EditDataInteractor): ViewModel {
        return EditProfileDataViewModel(
            editDataInteractor
        )
    }

    @EditDataScope
    @Provides
    fun provideEditProfileDataViewModelCreator(
        fragment: Fragment,
        viewModelFactory: ViewModelProvider.Factory
    ): EditProfileDataViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(EditProfileDataViewModel::class.java)


    @EditDataScope
    @Provides
    fun provideEditDataInteractor(editDataInteractor: EditDataInteractorImpl): EditDataInteractor =
        editDataInteractor

    @EditDataScope
    @Provides
    fun provideEditDataRepository(editDataRepository: EditDataRepositoryImpl): EditDataRepository =
        editDataRepository

    @EditDataScope
    @Provides
    fun provideService(apiFactory: ApiFactory) = apiFactory.editDataService

    @EditDataScope
    @Provides
    @IntoMap
    @ViewModelKey(ChangePasswordViewModel::class)
    fun provideChangePasswordViewModel(editDataInteractor: EditDataInteractor): ViewModel {
        return ChangePasswordViewModel(
            editDataInteractor
        )
    }

    @EditDataScope
    @Provides
    fun provideChangePasswordViewModelCreator(
        fragment: Fragment,
        viewModelFactory: ViewModelProvider.Factory
    ): ChangePasswordViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(ChangePasswordViewModel::class.java)
}
