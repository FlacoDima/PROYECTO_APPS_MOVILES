package com.example.proyectounilocal.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectounilocal.ui.screens.CreatePlace
import com.example.proyectounilocal.ui.screens.HomeScreen
import com.example.proyectounilocal.ui.screens.LoginForm
import com.example.proyectounilocal.ui.screens.ProfileEdition
import com.example.proyectounilocal.ui.screens.RecoverPassword
import com.example.proyectounilocal.ui.screens.RegisterForm

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val RECOVER = "recover"
    const val HOME = "home"
    const val EDIT_PROFILE = "edit_profile"
    const val CREATE_PLACE = "create_place"
}

@Composable
fun AppNav() {
    val nav = rememberNavController()
    NavHost(
        navController = nav,
        startDestination = Routes.LOGIN
    ) {
        login(nav)
        register(nav)
        recover(nav)
        home(nav)
        composable(Routes.EDIT_PROFILE) { ProfileEdition() }
        composable(Routes.CREATE_PLACE) { CreatePlace() }
    }
}

private fun NavGraphBuilder.login(nav: NavHostController) = composable(Routes.LOGIN) {
    LoginForm(
        onNavigateRegister = { nav.navigate(Routes.REGISTER) },
        onNavigateToRecover = { nav.navigate(Routes.RECOVER) },
        onNavigateHomeScreen = {
            nav.navigate(Routes.HOME) {
                popUpTo(Routes.LOGIN) { inclusive = true }
            }
        }
    )
}

private fun NavGraphBuilder.register(nav: NavHostController) = composable(Routes.REGISTER) {
    RegisterForm(
        // 👇 Flecha atrás: vuelve al Login que está debajo en el back stack
        onBack = { nav.popBackStack() },

        // 👇 Tras “Registro exitoso” también volvemos al Login
        onRegisterSuccess = { nav.popBackStack() }
    )
}

private fun NavGraphBuilder.recover(nav: NavHostController) = composable(Routes.RECOVER) {
    RecoverPassword(
        onBack = { nav.popBackStack() },
        onEmailSent = { nav.popBackStack() }
    )
}

private fun NavGraphBuilder.home(nav: NavHostController) = composable(Routes.HOME) {
    HomeScreen(
        onNavigateEditProfile = { nav.navigate(Routes.EDIT_PROFILE) },
        onNavigateCreatePlace = { nav.navigate(Routes.CREATE_PLACE) }
    )
}
