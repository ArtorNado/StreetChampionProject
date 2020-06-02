package com.example.streetchampionproject.registration.domain.interfaces

import com.example.streetchampionproject.login.data.models.UserId
import com.example.streetchampionproject.registration.data.model.User
import io.reactivex.Single

interface RegisterInteract {

    fun registration(u: User): Single<UserId>

}
