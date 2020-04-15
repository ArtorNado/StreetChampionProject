package com.example.streetchampionproject.login.data

import com.example.streetchampionproject.api.scs.StreetChampionService
import com.example.streetchampionproject.api.scs.response.AuthToken
import com.example.streetchampionproject.login.data.interfaces.LoginRepository
import com.example.streetchampionproject.login.data.models.UserId
import io.reactivex.Single
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private var streetChampionService: StreetChampionService
): LoginRepository{

    override fun logIn(userAuth: UserAuth):Single<AuthToken> = streetChampionService.logIn(userAuth)

    override fun userId(email: String):Single<UserId> = streetChampionService.getUserId(email)


}
