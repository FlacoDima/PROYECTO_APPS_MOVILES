package com.example.proyectounilocal.ui.components

import kotlinx.serialization.Serializable

sealed class RouteScreen {

    @Serializable
    data object Home : RouteScreen()

    @Serializable
    data object Login : RouteScreen()

    @Serializable
    data object Register : RouteScreen()

    @Serializable
    data object RecoverPassword : RouteScreen()

    @Serializable
    data object ProfileEdition : RouteScreen()

    @Serializable
    data object CreatePlace : RouteScreen()
}