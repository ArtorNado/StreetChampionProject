package com.example.streetchampionproject.notification.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.streetchampionproject.api.apiFactory.ApiFactory
import com.example.streetchampionproject.common.presentation.viewModel.ViewModelKey
import com.example.streetchampionproject.notification.data.NotificationRepository
import com.example.streetchampionproject.notification.data.NotificationRepositoryImpl
import com.example.streetchampionproject.notification.di.scope.NotificationScope
import com.example.streetchampionproject.notification.domain.NotificationInteractor
import com.example.streetchampionproject.notification.domain.NotificationInteractorImpl
import com.example.streetchampionproject.notification.presentation.NotificationViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class NotificationFeatureModule {

    @NotificationScope
    @Provides
    @IntoMap
    @ViewModelKey(NotificationViewModel::class)
    fun provideNotificationViewModel(
        notificationInteractor: NotificationInteractor,
        recipientId: Int
    ): ViewModel {
        return NotificationViewModel(
            notificationInteractor,
            recipientId
        )
    }

    @NotificationScope
    @Provides
    fun provideViewModelCreator(
        fragment: Fragment,
        viewModelFactory: ViewModelProvider.Factory
    ): NotificationViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(NotificationViewModel::class.java)


    @NotificationScope
    @Provides
    fun provideNotificationInteractor(notificationInteractor: NotificationInteractorImpl):
            NotificationInteractor = notificationInteractor

    @NotificationScope
    @Provides
    fun provideNotificationRepository(notificationRepository: NotificationRepositoryImpl):
            NotificationRepository = notificationRepository

    @NotificationScope
    @Provides
    fun provideService(apiFactory: ApiFactory) = apiFactory.notificationService
}
