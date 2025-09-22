package com.example.proyectounilocal.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectounilocal.ui.screens.CreatePlace
import com.example.proyectounilocal.ui.screens.HomeScreen
import com.example.proyectounilocal.ui.screens.LoginForm
import com.example.proyectounilocal.ui.screens.ProfileEdition
import com.example.proyectounilocal.ui.screens.RecoverPassword
import com.example.proyectounilocal.ui.screens.RegisterForm

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RouteScreen.Login
    ) {
        composable<RouteScreen.Login>{
            LoginForm(
                onNavigateRegister = {navController.navigate(RouteScreen.Register)},
                onNavigateToRecover = {navController.navigate(RouteScreen.RecoverPassword)},
                onNavigateHomeScreen = {navController.navigate(RouteScreen.Home)},
            )
        }
        composable<RouteScreen.Register>{ RegisterForm() }
        composable<RouteScreen.RecoverPassword>{ RecoverPassword() }
        composable<RouteScreen.Home>{
            HomeScreen(
                onNavigateCreatePlace = {navController.navigate(RouteScreen.CreatePlace) },
                onNavigateEditProfile = {navController.navigate(RouteScreen.ProfileEdition)}
            )
        }
        composable<RouteScreen.ProfileEdition>{ ProfileEdition() }
        composable<RouteScreen.CreatePlace>{ CreatePlace() }
    }
}