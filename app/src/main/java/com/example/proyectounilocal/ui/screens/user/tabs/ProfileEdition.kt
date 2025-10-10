//package com.example.proyectounilocal.ui.screens.user.tabs
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import com.example.proyectounilocal.R
//import androidx.compose.foundation.background
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.ui.graphics.Color
//
//@Composable
//fun ProfileEdition(navController: NavHostController) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Botón volver
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable { navController.popBackStack() }
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.ic_arrow_back),
//                contentDescription = null
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text("Editar perfil", fontWeight = FontWeight.Bold, fontSize = 20.sp)
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Image(
//            painter = painterResource(id = R.drawable.ic_person),
//            contentDescription = null,
//            modifier = Modifier
//                .size(120.dp)
//                .background(Color(0xFFEDE7F6), CircleShape)
//                .padding(16.dp)
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Button(onClick = { /* cambiar foto */ }) {
//            Text("Cambiar foto")
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // Campos de texto simulados
//        listOf("Nombre", "Usuario", "Ciudad", "Correo", "Contraseña").forEach {
//            OutlinedTextField(
//                value = "",
//                onValueChange = {},
//                label = { Text(it) },
//                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
//            )
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = { navController.popBackStack() },
//            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E57C2))
//        ) {
//            Text("Guardar cambios")
//        }
//    }
//}