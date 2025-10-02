package com.example.proyectounilocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.proyectounilocal.ui.components.AppNav
// Si tienes un theme composable propio, impórtalo y envuélvelo.

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Ejemplo si tienes un tema: ProyectoUniLocalTheme { AppNav() }
            AppNav()
        }
    }
}
