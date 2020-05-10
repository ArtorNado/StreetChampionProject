package com.example.streetchampionproject.app.navigation

import android.content.Context
import androidx.navigation.NavController
import com.example.streetchampionproject.login.presentation.LoginActivity
import com.example.streetchampionproject.login.router.LoginRouter
import com.example.streetchampionproject.main.presentation.MainActivity
import com.example.streetchampionproject.main.router.MainRouter
import com.example.streetchampionproject.registration.presentation.RegisterActivity
import com.example.streetchampionproject.registration.router.RegisterRouter

class Navigator : RegisterRouter, LoginRouter, MainRouter {

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

    override fun openMain(context: Context, id: Int) {
        MainActivity.start(context, id)
    }
}
