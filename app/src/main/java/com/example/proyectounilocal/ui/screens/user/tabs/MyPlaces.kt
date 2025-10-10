@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.proyectounilocal.ui.screens.user.tabs

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChatBubble
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow

/* ====== Snapshot simple para que EditPlace precargue el ítem correcto ====== */
data class PlaceSnapshot(val id: Int, val name: String, val category: String)

object PlaceSnapshotStore {
    val items = MutableStateFlow<Map<Int, PlaceSnapshot>>(emptyMap())
    fun setAll(list: List<PlaceSnapshot>) {
        items.value = list.associateBy { it.id }
    }
}

/* Colores del estilo general */
private val PageBg = Color(0xFFF5F1FF)
private val CardBg = Color(0xFFEFE8FF)
private val CardStroke = Color(0xFFD6CBFF)
private val ChipPurple = Color(0xFF6F57F6)
private val TextMain = Color(0xFF2F2A3B)

/* Datos temporales (mock) */
data class PlaceUi(val id: Int, val name: String, val category: String)

@Composable
fun MyPlaces(
    onEditPlace: (Int) -> Unit = {},
    onDeletePlace: (Int) -> Unit = {},
    onOpenComments: (Int) -> Unit = {}
) {
    val ctx = LocalContext.current

    // Lista temporal de lugares
    val places = remember {
        mutableStateListOf(
            PlaceUi(1, "Cafetería la plaza", "Cafeterías"),
            PlaceUi(2, "Donas & Café", "Cafeterías"),
            PlaceUi(3, "El Rincón Mexa", "Restaurantes")
        )
    }

    // Publica snapshot para que EditPlaceScreen y Comments precarguen el lugar correcto
    SideEffect {
        PlaceSnapshotStore.setAll(places.map { PlaceSnapshot(it.id, it.name, it.category) })
    }

    Scaffold(
        containerColor = PageBg,
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "Mis lugares",
                    color = TextMain,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(places, key = { it.id }) { place ->
                PlaceItem(
                    place = place,
                    onEdit = { onEditPlace(place.id) },
                    onDelete = {
                        places.remove(place)
                        onDeletePlace(place.id)
                        Toast.makeText(ctx, "Eliminado ${place.name}", Toast.LENGTH_SHORT).show()
                    },
                    onComments = { onOpenComments(place.id) }
                )
            }
        }
    }
}

/* ====== Tarjeta de lugar ====== */
@Composable
private fun PlaceItem(
    place: PlaceUi,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onComments: () -> Unit
) {
    val overrides by PlaceEdits.overrides.collectAsState()
    val edited = overrides[place.id]
    val shownName = edited?.name ?: place.name
    val shownCategory = edited?.category ?: place.category

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(width = 120.dp, height = 120.dp)
                .background(CardBg, RoundedCornerShape(16.dp))
                .border(1.dp, CardStroke, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) { Mountains() }

        Spacer(Modifier.height(12.dp))

        Text(
            text = shownName,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextMain,
            textAlign = TextAlign.Center
        )

        val category = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.SemiBold, color = TextMain)) { append("Categoría: ") }
            append(shownCategory)
        }
        Text(
            text = category,
            fontSize = 13.sp,
            color = TextMain.copy(alpha = 0.9f),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ChipButton("Editar", Icons.Outlined.Edit, onEdit)
            ChipButton("Eliminar", Icons.Outlined.Delete, onDelete)
            ChipButton("Comentarios", Icons.Outlined.ChatBubble, onComments)
        }
    }
}

/* ====== Botones redondeados ====== */
@Composable
private fun ChipButton(text: String, icon: ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
        colors = ButtonDefaults.buttonColors(containerColor = ChipPurple, contentColor = Color.White),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(16.dp))
        Spacer(Modifier.width(6.dp))
        Text(text, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

/* ====== Decoración ====== */
@Composable
private fun Mountains() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        val w = size.width
        val h = size.height
        val big = Path().apply {
            moveTo(w * 0.05f, h * 0.95f); lineTo(w * 0.50f, h * 0.35f); lineTo(w * 0.95f, h * 0.95f); close()
        }
        val small = Path().apply {
            moveTo(w * 0.20f, h * 0.95f); lineTo(w * 0.40f, h * 0.55f); lineTo(w * 0.60f, h * 0.95f); close()
        }
        drawPath(big, color = ChipPurple.copy(alpha = 0.20f))
        drawPath(small, color = ChipPurple.copy(alpha = 0.35f), style = Stroke(width = 3f))
    }
}
