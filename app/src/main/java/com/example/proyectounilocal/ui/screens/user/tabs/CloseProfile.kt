//package com.example.proyectounilocal.ui.screens.user.tabs
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.OutlinedButton
//import androidx.compose.material3.Text
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//fun CloseProfile(navController: NavHostController)
//{
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Card(
//            shape = RoundedCornerShape(16.dp),
//            modifier = Modifier.padding(16.dp),
//            colors = CardDefaults.cardColors(containerColor = Color.White)
//        ) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier.padding(24.dp)
//            ) {
//                Text(
//                    text = "¿Seguro que quieres cerrar sesión?",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Medium
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Row {
//                    Button(
//                        onClick = { /* acción de cerrar sesión */ },
//                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E57C2))
//                    ) {
//                        Text("Cerrar sesión")
//                    }
//                    Spacer(modifier = Modifier.width(12.dp))
//                    OutlinedButton(onClick = { navController.popBackStack() }) {
//                        Text("Cancelar")
//                    }
//                }
//            }
//        }
//    }
//}