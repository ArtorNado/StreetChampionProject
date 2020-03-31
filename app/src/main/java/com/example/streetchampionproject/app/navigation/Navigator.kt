package com.example.streetchampionproject.app.navigation

import android.content.Context
import androidx.navigation.NavController
import com.example.streetchampionproject.loginActivity.presentation.LoginActivity
import com.example.streetchampionproject.loginActivity.router.LoginRouter
import com.example.streetchampionproject.registerActivity.presentation.RegisterActivity
import com.example.streetchampionproject.registerActivity.router.RegisterRouter

class Navigator: RegisterRouter, LoginRouter {

    private var navController: NavController? = null

    fun attachNavController(navController: NavController, graph: Int) {
        navController.setGraph(graph)
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        if (this.navController == navController) {
            this.navController = null
        }
    }

    override fun openRegister(context: Context) {
        RegisterActivity.start(context)
    }

    override fun openLogin(context: Context) {
        LoginActivity.start(context)
    }
}
