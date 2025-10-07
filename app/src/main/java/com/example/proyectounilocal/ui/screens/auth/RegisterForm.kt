package com.example.proyectounilocal.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import com.example.proyectounilocal.R
import com.example.proyectounilocal.data.UserPrefs
import kotlinx.coroutines.launch

@Composable
fun RegisterForm(
    onBack: () -> Unit = {},
    onRegisterSuccess: () -> Unit = {}
) {
    val ctx = LocalContext.current
    val scope = rememberCoroutineScope()

    var fullName by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    val fieldShape = RoundedCornerShape(8.dp)
    val fieldColors = TextFieldDefaults.colors(
        focusedContainerColor = colorResource(R.color.purple_50),
        unfocusedContainerColor = colorResource(R.color.purple_50),
        disabledContainerColor = colorResource(R.color.purple_50),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = "Atrás")
            }
            Spacer(Modifier.weight(1f))
            Text(
                text = stringResource(R.string.txt_register_screen),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.weight(1f))
            Spacer(Modifier.width(48.dp))
        }

        Spacer(Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .size(92.dp)
                .clip(CircleShape)
                .background(colorResource(R.color.purple_50)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = stringResource(R.string.txt_perfil),
                tint = Color(0xFF5E35B1),
                modifier = Modifier.size(46.dp)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .offset(x = (-6).dp, y = 6.dp)
                    .size(22.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Agregar foto",
                    tint = colorResource(R.color.mi_color_fav),
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Label("Nombre completo :")
        TextField(
            value = fullName, onValueChange = { fullName = it },
            singleLine = true, shape = fieldShape, colors = fieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        Label("Nombre usuario:")
        TextField(
            value = username, onValueChange = { username = it },
            singleLine = true, shape = fieldShape, colors = fieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        Label("Correo electrónico:")
        TextField(
            value = email, onValueChange = { email = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            shape = fieldShape, colors = fieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        Label("Contraseña:")
        TextField(
            value = password, onValueChange = { password = it },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            shape = fieldShape, colors = fieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        Label("Ciudad residencia:")
        TextField(
            value = city, onValueChange = { city = it },
            singleLine = true, shape = fieldShape, colors = fieldColors,
            modifier = Modifier.fillMaxWidth()
        )

        if (error.isNotEmpty()) {
            Spacer(Modifier.height(6.dp))
            Text(text = error, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }

        Spacer(Modifier.height(22.dp))

        Button(
            onClick = {
                when {
                    fullName.isBlank() || username.isBlank() ||
                            email.isBlank() || password.isBlank() || city.isBlank() ->
                        error = "Todos los campos son obligatorios"
                    password.length < 8 ->
                        error = "La contraseña debe tener al menos 8 caracteres"
                    else -> {
                        error = ""
                        // Guarda también la contraseña (demo)
                        scope.launch {
                            UserPrefs.saveLastRegistered(
                                context = ctx,
                                username = username.trim(),
                                email = email.trim(),
                                fullName = fullName.trim(),
                                city = city.trim(),
                                password = password // 👈 pasa el pass
                            )
                        }
                        Toast.makeText(ctx, "Registro exitoso 🎉", Toast.LENGTH_SHORT).show()
                        onRegisterSuccess()
                    }
                }
            },
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.mi_color_fav),
                contentColor = colorResource(R.color.white)
            ),
            modifier = Modifier
                .width(160.dp)
                .height(42.dp)
        ) {
            Text(
                text = stringResource(R.string.btn_register),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun Label(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 6.dp)
    )
}
