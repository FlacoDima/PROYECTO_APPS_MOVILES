package com.example.proyectounilocal.ui.screens.user.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddPhotoAlternate
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectounilocal.R

@Composable
fun ProfileEdition() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 110.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Text(
                text = stringResource(R.string.txt_profile_edition),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
            )

            Icon(
                contentDescription = stringResource(R.string.txt_account),
                imageVector = Icons.Outlined.AccountCircle,
                modifier = Modifier.size(30.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {  },
            ) {
                Icon(
                    imageVector = Icons.Outlined.AddPhotoAlternate,
                    contentDescription = stringResource(R.string.btn_change_photo)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = stringResource(R.string.btn_change_photo))
            }

            OutlinedTextField(
                value = TextFieldValue(),
                onValueChange = {},
                modifier = Modifier,
                label = {
                    Text(text = stringResource(R.string.txt_name_person))
                }
            )

            OutlinedTextField(
                value = TextFieldValue(),
                onValueChange = {},
                modifier = Modifier,
                label = {
                    Text(text = stringResource(R.string.txt_user))
                }
            )

            OutlinedTextField(
                value = TextFieldValue(),
                onValueChange = {},
                modifier = Modifier,
                label = {
                    Text(text = stringResource(R.string.txt_city))
                }
            )

            OutlinedTextField(
                value = TextFieldValue(),
                onValueChange = {},
                modifier = Modifier,
                label = {
                    Text(text = stringResource(R.string.txt_mail))
                }
            )

            OutlinedTextField(
                value = TextFieldValue(),
                onValueChange = {},
                modifier = Modifier,
                label = {
                    Text(text = stringResource(R.string.txt_password))
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.mi_color_fav),
                    contentColor = colorResource(R.color.white)
                )
            ){
                Text(
                    text = stringResource(R.string.btn_save_changes),
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    )
}