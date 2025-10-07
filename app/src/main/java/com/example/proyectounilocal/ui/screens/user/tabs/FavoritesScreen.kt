package com.example.proyectounilocal.ui.screens.user.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectounilocal.R

data class FavoritePlace(
    val name: String,
    val category: String,
    val rating: Double,
    val image: Int
)

@Composable
fun FavoritesScreen(navController: NavController) {
    val favoritePlaces = listOf(
        FavoritePlace("Museo del oro", "Museo", 4.5, R.drawable.logo_unilocal),
        FavoritePlace("Hotel Mocawa Plaza", "Hotel", 5.0, R.drawable.logo_unilocal)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Favoritos",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(18.dp)) {
            items(favoritePlaces) { place ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF2E9FF), RoundedCornerShape(20.dp))
                        .padding(16.dp)
                        .clickable {
                            navController.navigate(
                                "place_detail/${place.name}/${place.category}/${place.rating}"
                            )
                        }
                ) {
                    FavoriteItem(place)
                }
            }
        }
    }
}

@Composable
fun FavoriteItem(place: FavoritePlace) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color(0xFFE8DFFF), RoundedCornerShape(15.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = place.image),
                contentDescription = "Imagen del lugar",
                modifier = Modifier.size(60.dp)
            )
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Favorito",
                tint = Color.Red,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(22.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = place.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Text(
                text = "Categoría: ${place.category}",
                color = Color.DarkGray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            RatingStars(place.rating)
        }
    }
}

@Composable
fun RatingStars(rating: Double) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(5) { index ->
            val tint = if (index < rating.toInt()) Color.Yellow else Color.LightGray
            Icon(
                painter = painterResource(id = android.R.drawable.btn_star_big_on),
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = rating.toString(),
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}
