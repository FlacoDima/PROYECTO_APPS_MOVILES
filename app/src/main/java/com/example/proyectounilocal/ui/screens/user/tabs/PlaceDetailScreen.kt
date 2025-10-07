package com.example.proyectounilocal.ui.screens.user.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectounilocal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailScreen(
    navController: NavController,
    placeName: String,
    category: String,
    rating: Double
) {
    Scaffold(
        topBar = {
            // ✅ Reemplazo de SmallTopAppBar por CenterAlignedTopAppBar
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = placeName,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Atrás"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFF2E9FF)
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_unilocal),
                contentDescription = "Imagen del lugar",
                modifier = Modifier
                    .size(180.dp)
                    .background(Color(0xFFF2E9FF), RoundedCornerShape(20.dp))
                    .padding(20.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Categoría: $category", fontSize = 16.sp, color = Color.Black)
            Text(text = "Calificación: ⭐ $rating", fontSize = 16.sp, color = Color.Black)

            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Aquí podrás ver la descripción, dirección y comentarios del lugar.",
                fontSize = 15.sp,
                color = Color.DarkGray
            )
        }
    }
}
