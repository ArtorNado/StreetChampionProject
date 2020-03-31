package com.example.streetchampionproject.registerActivity.domain

import com.example.streetchampionproject.registerActivity.data.RegisterRepository
import com.example.streetchampionproject.registerActivity.data.model.User

class RegisterInteract(private val registerRepository: RegisterRepository) {

    fun registration(u: User) {
        registerRepository.registration(u)
    }
}
