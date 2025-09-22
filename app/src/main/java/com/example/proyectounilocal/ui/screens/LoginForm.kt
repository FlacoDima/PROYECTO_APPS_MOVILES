package com.example.proyectounilocal.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.ui.res.colorResource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.example.proyectounilocal.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 110.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Image(
                painter = painterResource(R.drawable.unilocal),
                contentDescription = stringResource(R.string.txt_perfil),
                modifier = Modifier
                    .size(80.dp)
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(R.string.txt_app),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
            )

            Spacer(modifier = Modifier.height(60.dp))

            OutlinedTextField(
                label = {
                    Text(text = stringResource(R.string.txt_user))
                },
                leadingIcon = {
                    Icon(
                        contentDescription = stringResource(R.string.txt_user),
                        imageVector = Icons.Rounded.Person
                    )
                },
                value = user,
                isError = isUserError,
                supportingText = {
                    if (isUserError) {
                        Text(text = stringResource(R.string.txt_user_error))
                    }
                },

                onValueChange = {
                    user = it
                    isUserError = user.isBlank()
                },
            )

            OutlinedTextField(
                label = {
                    Text(text = stringResource(R.string.txt_password))
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.key),
                        contentDescription = stringResource(R.string.txt_password)
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                value = password,
                isError = isPasswordError,
                supportingText = {
                    if (isPasswordError) {
                        Text(text = stringResource(R.string.txt_password_error))
                    }
                },
                onValueChange = {
                    password = it
                    isPasswordError = password.isBlank() || password.length < 8
                },
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = onNavigateToRecover) {
                Text(
                    text = stringResource(R.string.txt_recover),
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
            val mensaje = stringResource(R.string.txt_error)
            val mensaje2 = stringResource(R.string.txt_message_welcome)

            Button (onClick = {
                if (user == "jonathan" && password == "12345678") {
                    Toast.makeText(context, mensaje2, Toast.LENGTH_SHORT).show()
                    onNavigateHomeScreen()
                }
                else
                    Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
            },
                enabled = !isUserError && !isPasswordError,
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text(
                    text = stringResource(R.string.btn_login),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = stringResource(R.string.txt_create_count),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    onNavigateRegister()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.purpura),
                    contentColor = colorResource(R.color.mi_color_fav)
                )
            ){
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = stringResource(R.string.txt_perfil)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(R.string.btn_register),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}