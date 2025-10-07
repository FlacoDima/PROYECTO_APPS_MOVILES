package com.example.proyectounilocal.ui.screens.user.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Comments(
    placeId: Int,
    onBack: () -> Unit
) {
    val commentText = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Comentarios",
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFF2E9FF)
                )
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color(0xFFF2E9FF), RoundedCornerShape(20.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                BasicTextField(
                    value = commentText.value,
                    onValueChange = { commentText.value = it },
                    textStyle = TextStyle(color = Color.Black, fontSize = 14.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    decorationBox = { innerTextField ->
                        if (commentText.value.isEmpty()) {
                            Text(
                                text = "Escribe una respuesta...",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                        innerTextField()
                    }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CommentCard(
                userName = "Ana Gómez",
                date = "10/02/2025",
                text = "El mejor café de la ciudad, el aroma y el sabor son increíbles. La atención fue rápida y muy cordial.",
                rating = 5.0
            )

            CommentCard(
                userName = "Carlos Pérez",
                date = "14/02/2025",
                text = "Lugar perfecto para pasar la tarde. El café es excelente, aunque el espacio en la sala no es muy amplio.",
                rating = 4.5
            )

            CommentCard(
                userName = "Luisa Ramírez",
                date = "18/02/2025",
                text = "No está mal, pero la atención al cliente fue demorada. Creo que podrían mejorar la rapidez.",
                rating = 4.0
            )
        }
    }
}

@Composable
fun CommentCard(
    userName: String,
    date: String,
    text: String,
    rating: Double
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF2E9FF), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "⭐ ${rating}", fontSize = 14.sp)
            Text(text = date, fontSize = 12.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = userName,
            fontSize = 15.sp,
            color = Color.Black,
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.DarkGray,
            lineHeight = 18.sp
        )
    }
}
