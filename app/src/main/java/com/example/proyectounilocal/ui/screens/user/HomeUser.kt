package com.example.proyectounilocal.ui.screens.user

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.proyectounilocal.ui.screens.user.bottombar.NavigationBar
import com.example.proyectounilocal.ui.screens.user.nav.Content

@Composable
fun HomeUser() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        bottomBar = { NavigationBar(navController = navController) }
    ) { padding ->
        Content(
            navController = navController,
            padding = padding
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "UNILOCAL",
                modifier = Modifier.padding(16.dp),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    )
}
