package com.example.proyectounilocal.ui.screens.user.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.proyectounilocal.R
import com.example.proyectounilocal.ui.screens.user.nav.RouteTab

@Composable
fun NavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        Destination.entries.forEach { destination ->
            val isSelected = currentDestination?.route == destination.route::class.simpleName

            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = colorResource(R.color.mi_color_fav)
                ),
                onClick = {
                    navController.navigate(destination.route::class.simpleName!!) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = stringResource(destination.contentDescription)
                    )
                },
                selected = isSelected
            )
        }
    }
}

enum class Destination(
    val route: RouteTab,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val contentDescription: Int
) {
    HomeScreen(RouteTab.MapScreen, Icons.Filled.Home, R.string.menu_home),
    Favorites(RouteTab.Favorites, Icons.Filled.Favorite, R.string.menu_favorites),
    CreatePlace(RouteTab.CreatePlace, Icons.Filled.Add, R.string.menu_CreatePlace),
    MyPlaces(RouteTab.MyPlaces, Icons.Outlined.LocationOn, R.string.menu_MyPlaces),
    Profile(RouteTab.Profile, Icons.Filled.AccountCircle, R.string.menu_ProfileEdition)
}
