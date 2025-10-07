package com.example.proyectounilocal.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectounilocal.R
import com.example.proyectounilocal.data.UserPrefs

@Composable
fun LoginForm(
    onNavigateRegister: () -> Unit,
    onNavigateToRecover: () -> Unit,
    onNavigateHomeScreen: () -> Unit
) {
    var user by rememberSaveable { mutableStateOf("") }
    var isUserError by rememberSaveable { mutableStateOf(false) }
    var password by rememberSaveable { mutableStateOf("") }
    var isPasswordError by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    // Prefill username y lectura de password guardado (solo demo)
    val lastUser by UserPrefs.lastUsername(context).collectAsState(initial = "")
    val lastPass by UserPrefs.lastPassword(context).collectAsState(initial = "") // 👈 nuevo
    LaunchedEffect(lastUser) {
        if (user.isBlank() && lastUser.isNotBlank()) user = lastUser
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo_unilocal),
            contentDescription = stringResource(R.string.txt_perfil),
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 12.dp)
        )

        Spacer(Modifier.height(20.dp))

        val fieldShape = RoundedCornerShape(8.dp)
        val fieldColors = TextFieldDefaults.colors(
            focusedContainerColor = colorResource(R.color.purple_50),
            unfocusedContainerColor = colorResource(R.color.purple_50),
            disabledContainerColor = colorResource(R.color.purple_50),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )

        TextField(
            value = user,
            onValueChange = {
                user = it
                isUserError = user.isBlank()
            },
            isError = isUserError,
            supportingText = { if (isUserError) Text(stringResource(R.string.txt_user_error)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = stringResource(R.string.txt_user)
                )
            },
            label = { Text(stringResource(R.string.txt_user)) },
            singleLine = true,
            shape = fieldShape,
            colors = fieldColors,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        TextField(
            value = password,
            onValueChange = {
                password = it
                isPasswordError = password.isBlank() || password.length < 8
            },
            isError = isPasswordError,
            supportingText = { if (isPasswordError) Text(stringResource(R.string.txt_password_error)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.key),
                    contentDescription = stringResource(R.string.txt_password)
                )
            },
            label = { Text(stringResource(R.string.txt_password)) },
            singleLine = true,
            shape = fieldShape,
            colors = fieldColors,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.txt_recover),
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            modifier = Modifier
                .padding(top = 6.dp, bottom = 2.dp)
                .clickable { onNavigateToRecover() }
        )

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = {
                val mensaje = context.getString(R.string.txt_error)
                val mensaje2 = context.getString(R.string.txt_message_welcome)

                // ✅ Ahora valida contra:
                // 1) el usuario/contraseña guardados por el registro (demo local), o
                // 2) las credenciales hardcodeadas que ya tenías
                val okFromPrefs = (user == lastUser && password == lastPass)
                val okHardcoded = (user == "jonathan" && password == "12345678")

                if ((okFromPrefs || okHardcoded) && !isUserError && !isPasswordError) {
                    Toast.makeText(context, mensaje2, Toast.LENGTH_SHORT).show()
                    onNavigateHomeScreen()
                } else {
                    Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
                }
            },
            enabled = !isUserError && !isPasswordError,
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.mi_color_fav),
                contentColor = colorResource(R.color.white)
            ),
            modifier = Modifier
                .width(140.dp)
                .height(40.dp)
        ) {
            Text(
                text = stringResource(R.string.btn_login),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(14.dp))

        OutlinedButton(
            onClick = onNavigateRegister,
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = colorResource(R.color.mi_color_fav)
            ),
            border = BorderStroke(1.dp, colorResource(R.color.mi_color_fav)),
            modifier = Modifier
                .width(160.dp)
                .height(40.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = stringResource(R.string.txt_perfil)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.btn_register),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
