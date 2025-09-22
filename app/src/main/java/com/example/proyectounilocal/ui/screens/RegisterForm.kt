package com.example.proyectounilocal.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectounilocal.R

@Composable
fun RegisterForm(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 110.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Text(
                text = stringResource(R.string.txt_register_screen),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
            )

            Spacer(modifier = Modifier.height(30.dp))

            Image(
                imageVector = Icons.Rounded.AddCircle,
                contentDescription = R.string.txt_icon_register.toString(),
                modifier = Modifier
                    .size(100.dp)
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier,
                label = {
                    Text(text = stringResource(R.string.txt_name_complete))
                }
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier,
                label = {
                    Text(text = stringResource(R.string.txt_name_user))
                }
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier,
                label = {
                    Text(text = stringResource(R.string.txt_email))
                }
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier,
                label = {
                    Text(text = stringResource(R.string.txt_password))
                }
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier,
                label = {
                    Text(text = stringResource(R.string.txt_residence_city))
                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.mi_color_fav),
                    contentColor = colorResource(R.color.white)
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = stringResource(R.string.txt_perfil)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(R.string.btn_register),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    )
}