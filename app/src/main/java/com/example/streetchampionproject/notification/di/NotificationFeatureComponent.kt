package com.example.streetchampionproject.notification.di

import com.example.streetchampionproject.notification.di.scope.NotificationScope
import com.example.streetchampionproject.notification.presentation.NotificationFragment
import dagger.BindsInstance
import dagger.Subcomponent

@NotificationScope
@Subcomponent(modules = [NotificationFeatureModule::class])
interface NotificationFeatureComponent {

    fun inject(notificationFragment: NotificationFragment)

    @Subcomponent.Builder
    interface Builder{

        fun build(): NotificationFeatureComponent

        @BindsInstance
        fun create(recipientId: Int): Builder
    }
}
