package com.example.proyectounilocal.ui.screens.user.tabs

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectounilocal.R
import com.example.proyectounilocal.ui.screens.user.nav.RouteTab

@Composable
fun MapaScreen(navController: NavController) {
    var searchText by remember{mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.mapa),
            contentDescription = stringResource(R.string.txt_map_location),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navController.navigate(RouteTab.MapDetailScreen::class.simpleName!!)
                },
        )

        val context = LocalContext.current

        // 🔍 Barra de búsqueda
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            placeholder = { Text(stringResource(R.string.txt_search), fontSize = 14.sp) },
            leadingIcon = { Icon(Icons.Default.Tune, contentDescription = null) },
            trailingIcon = {
                val message = stringResource(R.string.txt_iconSearch)

                IconButton(onClick = {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()})
                {
                    Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.txt_search))
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color(0xFFF3E5F5),
                unfocusedContainerColor = Color(0xFFF3E5F5),
                disabledContainerColor = Color(0xFFF3E5F5)
            ),
            textStyle = TextStyle(fontSize = 16.sp)
        )
    }
}
