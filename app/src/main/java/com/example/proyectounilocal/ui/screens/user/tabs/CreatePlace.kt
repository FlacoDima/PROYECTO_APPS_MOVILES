package com.example.proyectounilocal.ui.screens.user.tabs

import android.graphics.drawable.Icon
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectounilocal.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

/* ====== Paleta ====== */
private val FieldBg = Color(0xFFF3EEFF)
private val FieldStroke = Color(0xFFD7CCFF)
private val Primary = Color(0xFF7B61FF)

private val ChipPurple = Color(0xFF6F57F6)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlace() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(-4.dp)
    ){
        var nombre by remember { mutableStateOf("") }
        var descripcion by remember { mutableStateOf("") }
        var categoria by remember { mutableStateOf("") }
        var horario by remember { mutableStateOf("") }
        var telefono by remember { mutableStateOf("") }

        // Texto de titulo
        Text(
            text = stringResource(R.string.txt_create_place),
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )

        // Campo Nombre
        StyleField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(stringResource(R.string.txt_name_place)) }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Campo Descripción
        StyleField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text(stringResource(R.string.txt_descripcion)) }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Categoría
        var expanded by remember { mutableStateOf(false) }
        val categorias = listOf("Restaurante", "Parque", "Museo", "Café", "Otro")

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            StyleField(
                value = categoria,
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.txt_category)) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categorias.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            categoria = item
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Campo Horario
        StyleField(
            value = horario,
            onValueChange = { horario = it },
            label = { Text(stringResource(R.string.txt_schedule)) }
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Campo Teléfono
        StyleField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text(stringResource(R.string.txt_phone)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Cargar Imagen
            Text(
                text = stringResource(R.string.txt_load_image),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(5.dp))

            val context = LocalContext.current
            var imagen = stringResource(R.string.txt_loading_image)

            IconButton(
                onClick = {
                    Toast.makeText(context, imagen, Toast.LENGTH_SHORT).show()
                }
            ){
                Icon(
                    imageVector = Icons.Default.AddPhotoAlternate,
                    contentDescription = "Cargar imagen",
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        // Imagen de mapa (de ejemplo)
        Image(
            painter = painterResource(R.drawable.mapa_placeholder),
            contentDescription = stringResource(R.string.txt_map),
            modifier = Modifier
                .height(80.dp)
                .width(100.dp)
                .clip(RoundedCornerShape(1.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(40.dp))

        var context = LocalContext.current

        // Botón Publicar
        Button(
            onClick = {
                // 1️⃣ Crear inflater para inflar un layout XML personalizado
                val layout = LayoutInflater.from(context)
                    .inflate(R.layout.custom_toast_layout, null)

                // 4️⃣ Crear y mostrar el Toast
                with(Toast(context)) {
                    duration = Toast.LENGTH_SHORT
                    view = layout
                    show()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = ChipPurple, contentColor = Color.White),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .wrapContentWidth()
                .height(50.dp)
        ) {
            Icon(
                imageVector = Icons.Default.FileUpload,
                contentDescription = stringResource(R.string.txt_publish),
                tint = Color.White
            )

            Spacer(modifier = Modifier.width(15.dp))

            Text(stringResource(R.string.txt_publish), fontSize = 18.sp, color = Color.White)
        }
    }
}

/* ============ COMPONENTE REUTILIZABLE PARA CAMPOS DE TEXTO ============ */
@Composable
private fun StyleField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit),
    singleLine: Boolean = true,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        singleLine = singleLine,
        readOnly = readOnly,
        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        colors = OutlinedTextFieldDefaults.colors(
        unfocusedContainerColor = FieldBg,
        focusedContainerColor = FieldBg,
        unfocusedBorderColor = FieldStroke,
        focusedBorderColor = Primary.copy(alpha = 0.7f),
        cursorColor = Primary
        )
    )
}
