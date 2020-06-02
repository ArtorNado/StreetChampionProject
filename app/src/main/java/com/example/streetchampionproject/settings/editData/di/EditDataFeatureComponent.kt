package com.example.streetchampionproject.settings.editData.di

import androidx.fragment.app.Fragment
import com.example.streetchampionproject.settings.editData.di.scope.EditDataScope
import com.example.streetchampionproject.settings.editData.presentation.changePassword.ChangePasswordFragment
import com.example.streetchampionproject.settings.editData.presentation.editProfileData.EditProfileDataFragment
import dagger.BindsInstance
import dagger.Subcomponent

@EditDataScope
@Subcomponent(modules = [EditDataFeatureModule::class])
interface EditDataFeatureComponent {

    fun inject(editProfileDataFragment: EditProfileDataFragment)

    fun inject(changePasswordFragment: ChangePasswordFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): EditDataFeatureComponent

        @BindsInstance
        fun withFragment(fragment: Fragment): Builder

    }
}
