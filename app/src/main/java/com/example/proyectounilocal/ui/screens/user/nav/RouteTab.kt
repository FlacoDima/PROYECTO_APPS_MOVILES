package com.example.proyectounilocal.ui.screens.user.nav

import kotlinx.serialization.Serializable

sealed class RouteTab {
    @Serializable object MapScreen : RouteTab()
    @Serializable object Favorites : RouteTab()
    @Serializable object CreatePlace : RouteTab()
    @Serializable object MyPlaces : RouteTab()

    @Serializable object Profile : RouteTab()

    // Rutas extra
    @Serializable object EditPlace : RouteTab()
    @Serializable object Comments : RouteTab()

    @Serializable object MapDetailScreen : RouteTab()

    @Serializable object ProfileEdition : RouteTab()

    @Serializable object CloseProfile : RouteTab()
}
