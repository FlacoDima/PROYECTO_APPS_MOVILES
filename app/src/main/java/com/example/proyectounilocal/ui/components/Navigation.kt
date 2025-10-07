package com.example.proyectounilocal.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectounilocal.ui.screens.auth.LoginForm
import com.example.proyectounilocal.ui.screens.auth.RegisterForm
import com.example.proyectounilocal.ui.screens.auth.RecoverPassword
import com.example.proyectounilocal.ui.screens.user.HomeUser

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val RECOVER = "recover"
    const val HOME_USER = "home_user"
}

@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        addLogin(navController)
        addRegister(navController)
        addRecover(navController)
        addHomeUser(navController)
    }
}

/* ---------- Login ---------- */
private fun NavGraphBuilder.addLogin(nav: NavHostController) {
    composable(Routes.LOGIN) {
        LoginForm(
            onNavigateRegister = { nav.navigate(Routes.REGISTER) },
            onNavigateToRecover = { nav.navigate(Routes.RECOVER) },
            onNavigateHomeScreen = {
                nav.navigate(Routes.HOME_USER) {
                    popUpTo(Routes.LOGIN) { inclusive = true }
                }
            }
        )
    }
}

/* ---------- Registro ---------- */
private fun NavGraphBuilder.addRegister(nav: NavHostController) {
    composable(Routes.REGISTER) {
        RegisterForm(
            onBack = { nav.popBackStack() },
            onRegisterSuccess = { nav.popBackStack() } // vuelve al login
        )
    }
}

/* ---------- Recuperar contraseña ---------- */
private fun NavGraphBuilder.addRecover(nav: NavHostController) {
    composable(Routes.RECOVER) {
        RecoverPassword(
            onBack = { nav.popBackStack() },
            onEmailSent = { nav.popBackStack() }
        )
    }
}

/* ---------- HomeUser (flujo de usuario) ---------- */
private fun NavGraphBuilder.addHomeUser(nav: NavHostController) {
    composable(Routes.HOME_USER) {
        HomeUser()
    }
}
