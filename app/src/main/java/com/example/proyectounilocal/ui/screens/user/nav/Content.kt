package com.example.proyectounilocal.ui.screens.user.nav
import com.example.proyectounilocal.ui.screens.user.tabs.MapaScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyectounilocal.ui.screens.user.tabs.*

@Composable
fun Content(
    padding: PaddingValues,
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier.padding(padding),
        navController = navController,
        startDestination = RouteTab.MapScreen::class.simpleName!!
    ) {

        // 🗺️ Pantalla principal (mapa)
        composable(RouteTab.MapScreen::class.simpleName!!) {
            MapaScreen(navController)
        }

        // 🗺️ Pantalla detalle mapa
        composable(RouteTab.MapDetailScreen::class.simpleName!!) {
            MapDetail(
                onBack = { navController.popBackStack() }
            )
        }

        // 💜 Favoritos
        composable(RouteTab.Favorites::class.simpleName!!) {
            FavoritesScreen(navController = navController)
        }

        // ➕ Crear lugar
        composable(RouteTab.CreatePlace::class.simpleName!!) {
            CreatePlace()
        }

        // 📍 Mis lugares
        composable(RouteTab.MyPlaces::class.simpleName!!) {
            MyPlaces(
                onEditPlace = { id ->
                    navController.navigate(RouteTab.EditPlace::class.simpleName!! + "/$id")
                },
                onOpenComments = { id ->
                    navController.navigate(RouteTab.Comments::class.simpleName!! + "/$id")
                }
            )
        }

        // 👤 Perfil (principal)
        composable(RouteTab.Profile::class.simpleName!!) {
            Profile()
        }

        // 👤 Edición de perfil
        composable(RouteTab.ProfileEdition::class.simpleName!!) {
//            ProfileEdition(navController)
        }

        // ⚠️ Confirmar cierre de sesión
        composable(RouteTab.CloseProfile::class.simpleName!!) {
//            CloseProfile(navController)
        }

        // ⚙️ Ruta adicional: edición de lugar
        composable(RouteTab.EditPlace::class.simpleName!! + "/{id}") { backStack ->
            val id = backStack.arguments?.getString("id")?.toIntOrNull() ?: 0
            EditPlaceScreen(
                placeId = id,
                onBack = { navController.popBackStack() },
                onSaved = { navController.popBackStack() }
            )
        }

        // 💬 Ruta adicional: comentarios
        composable(RouteTab.Comments::class.simpleName!! + "/{id}") { backStack ->
            val id = backStack.arguments?.getString("id")?.toIntOrNull() ?: 0
            Comments(
                placeId = id,
                onBack = { navController.popBackStack() }
            )
        }

        // 🏛️ Nueva ruta: detalle del lugar (desde favoritos)
        composable(
            route = "place_detail/{name}/{category}/{rating}"
        ) { backStack ->
            val name = backStack.arguments?.getString("name") ?: ""
            val category = backStack.arguments?.getString("category") ?: ""
            val rating = backStack.arguments?.getString("rating")?.toDoubleOrNull() ?: 0.0

            PlaceDetailScreen(
                navController = navController,
                placeName = name,
                category = category,
                rating = rating
            )
        }
    }
}
