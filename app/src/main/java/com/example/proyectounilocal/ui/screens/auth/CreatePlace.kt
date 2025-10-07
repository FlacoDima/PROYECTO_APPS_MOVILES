package com.example.proyectounilocal.ui.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.InsertPhoto
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
fun CreatePlace() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 110.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Text(
                text = stringResource(R.string.txt_create_place),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = TextFieldValue(),
                onValueChange = {},
                modifier = Modifier,
                label = {
                    Text(text = stringResource(R.string.txt_name_place))
                }
            )

            OutlinedTextField(
                value = TextFieldValue(),
                onValueChange = {},
                modifier = Modifier,
                label = {
                    Text(text = stringResource(R.string.txt_descripcion))
                }
            )

            OutlinedTextField(
                value = TextFieldValue(),
                onValueChange = {},
                modifier = Modifier,
                label = {
                    Text(text = stringResource(R.string.txt_category))
                }
            )

            OutlinedTextField(
                value = TextFieldValue(),
                onValueChange = {},
                modifier = Modifier,
                label = {
                    Text(text = stringResource(R.string.txt_schedule))
                }
            )

            OutlinedTextField(
                value = TextFieldValue(),
                onValueChange = {},
                modifier = Modifier,
                label = {
                    Text(text = stringResource(R.string.txt_phone))
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier,
            ) {
                Text(
                    text = stringResource(R.string.txt_load_image),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
                Icon(
                    contentDescription = stringResource(R.string.txt_load_image),
                    imageVector = Icons.Outlined.InsertPhoto
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.mi_color_fav),
                    contentColor = colorResource(R.color.white)
                )
            ){
                Text(
                    text = stringResource(R.string.txt_publish),
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    )
}
