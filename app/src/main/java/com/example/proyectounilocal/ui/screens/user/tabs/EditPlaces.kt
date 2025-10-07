@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.proyectounilocal.ui.screens.user.tabs

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Collections
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

/* ====== Store de overrides para reflejar cambios en MyPlaces ====== */
data class SimpleEdit(val name: String, val category: String)
object PlaceEdits {
    val overrides = MutableStateFlow<Map<Int, SimpleEdit>>(emptyMap())
    fun set(id: Int, name: String, category: String) {
        overrides.update { it + (id to SimpleEdit(name.trim(), category.trim())) }
    }
}

/* ====== Paleta ====== */
private val FieldBg = Color(0xFFF3EEFF)
private val FieldStroke = Color(0xFFD7CCFF)
private val Primary = Color(0xFF7B61FF)
private val TextMain = Color(0xFF2A2731)

/* ===================== PANTALLA DE EDICIÓN ===================== */
@Composable
fun EditPlaceScreen(
    placeId: Int,
    onBack: () -> Unit,
    onSaved: () -> Unit
) {
    val ctx = LocalContext.current

    // ✅ Precarga desde MyPlaces (snapshot) y aplica override si ya hubo edición
    val snapshot by PlaceSnapshotStore.items.collectAsState()
    val base = snapshot[placeId]?.let { SimpleEdit(it.name, it.category) }
    val overrides by PlaceEdits.overrides.collectAsState()
    val prior = overrides[placeId] ?: base

    var name by remember(placeId, prior?.name) { mutableStateOf(prior?.name ?: "Cafetería la plaza") }
    var description by remember { mutableStateOf("Un lugar acogedor para disfrutar de café y postres…") }
    val allCategories = listOf("Restaurantes", "Cafeterías", "Comidas rápidas", "Museos", "Hoteles")
    var selectedCategory by remember(placeId, prior?.category) { mutableStateOf(prior?.category ?: "Cafeterías") }
    var expanded by remember { mutableStateOf(false) }
    var schedule by remember { mutableStateOf("Lunes a sábado, 8:00 a 20:00") }
    var phone by remember { mutableStateOf("310 456 7890") }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Outlined.ArrowBack, contentDescription = "Atrás", tint = TextMain)
                    }
                },
                title = {
                    Text(
                        "Editar Lugar",
                        color = TextMain,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = { /* subir imagen */ }) {
                        Icon(Icons.Outlined.Image, contentDescription = "Imagen", tint = Primary)
                    }
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        PlaceEdits.set(placeId, name, selectedCategory)   // publica cambios
                        Toast.makeText(ctx, "Lugar actualizado", Toast.LENGTH_SHORT).show()
                        onSaved() // navega atrás
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    modifier = Modifier.width(160.dp)
                ) {
                    Text("Editar", color = Color.White, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Nombre
            FieldLabel("Nombre del lugar:")
            StyledField(value = name, onValueChange = { name = it }, singleLine = true)

            // Descripción
            FieldLabel("Descripción:")
            StyledField(
                value = description,
                onValueChange = { description = it },
                singleLine = false,
                modifier = Modifier.heightIn(min = 110.dp)
            )

            // Categoría
            FieldLabel("Categoría:")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(FieldBg, RoundedCornerShape(8.dp))
                    .border(1.dp, FieldStroke, RoundedCornerShape(8.dp))
                    .clickable { expanded = true }
                    .padding(horizontal = 12.dp, vertical = 14.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        selectedCategory,
                        modifier = Modifier.weight(1f),
                        color = TextMain,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null, tint = TextMain)
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    allCategories.forEach { cat ->
                        DropdownMenuItem(
                            text = { Text(cat) },
                            onClick = { selectedCategory = cat; expanded = false }
                        )
                    }
                }
            }

            // Horario
            FieldLabel("Horario:")
            StyledField(value = schedule, onValueChange = { schedule = it }, singleLine = true)

            // Teléfono
            FieldLabel("Teléfono:")
            StyledField(value = phone, onValueChange = { phone = it }, singleLine = true)

            // Cargar imagen
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Cargar imagen:", color = TextMain, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.width(6.dp))
                Icon(
                    Icons.Outlined.Collections,
                    contentDescription = null,
                    tint = Primary,
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .clickable { /* abrir picker */ }
                )
            }

            // Recuadros: Imagen / Mapa
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PlaceholderCard("Imagen", Icons.Outlined.Image, Modifier.weight(1f))
                PlaceholderCard("Mapa", Icons.Outlined.Map, Modifier.weight(1f))
            }
        }
    }
}

/* ===================== COMPONENTES REUTILIZABLES ===================== */
@Composable
private fun FieldLabel(text: String) {
    Text(text, color = TextMain, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
}

@Composable
private fun StyledField(
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(FieldBg, RoundedCornerShape(8.dp))
            .border(1.dp, FieldStroke, RoundedCornerShape(8.dp)),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = FieldBg,
            focusedContainerColor = FieldBg,
            unfocusedBorderColor = FieldStroke,
            focusedBorderColor = Primary.copy(alpha = 0.7f),
            cursorColor = Primary
        )
    )
}

@Composable
private fun PlaceholderCard(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(110.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .border(1.dp, FieldStroke, RoundedCornerShape(16.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, contentDescription = label, tint = Primary, modifier = Modifier.size(28.dp))
        Spacer(Modifier.height(6.dp))
        Text(label, color = TextMain, fontSize = 12.sp)
    }
}
