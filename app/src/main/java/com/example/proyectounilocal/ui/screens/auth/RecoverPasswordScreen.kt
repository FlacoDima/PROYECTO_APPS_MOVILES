package com.example.proyectounilocal.ui.screens.auth

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.statusBarsPadding
import com.example.proyectounilocal.R

@Composable
fun RecoverPassword(
    onBack: () -> Unit = {},
    onEmailSent: (String) -> Unit = {}
) {
    val ctx = LocalContext.current
    var email by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    fun validateEmail(text: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(text.trim()).matches()

    Scaffold(
        topBar = { SimpleTopBar(onBack) }
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Recuperar contraseña",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Te enviaremos un enlace a tu\ncorreo electrónico para recuperar la\ncontraseña",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp
            )

            Spacer(Modifier.height(26.dp))

            Text(
                text = "Correo electrónico :",
                fontSize = 14.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    if (isError) isError = false
                },
                singleLine = true,
                isError = isError,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                supportingText = { if (isError) Text("Ingresa un correo válido") }
            )

            Spacer(Modifier.height(22.dp))

            Button(
                onClick = {
                    val ok = validateEmail(email)
                    isError = !ok
                    if (ok) {
                        Toast.makeText(
                            ctx,
                            "Si el correo existe, te enviamos un enlace",
                            Toast.LENGTH_SHORT
                        ).show()
                        onEmailSent(email.trim())
                    } else {
                        Toast.makeText(ctx, "Correo inválido", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.mi_color_fav),
                    contentColor = colorResource(R.color.white)
                ),
                modifier = Modifier
                    .width(140.dp)
                    .height(45.dp)
            ) {
                Text("Enviar", fontWeight = FontWeight.Bold, fontSize = 15.sp)
            }
        }
    }
}

@Composable
private fun SimpleTopBar(
    onBack: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(56.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween   // 🔹 reparte extremos
    ) {
        // Flecha atrás
        IconButton(onClick = onBack) {
            Icon(Icons.Rounded.ArrowBack, contentDescription = "Atrás")
        }

        // Logo a la derecha (sin Spacer)
        Image(
            painter = painterResource(R.drawable.logo2),
            contentDescription = "Logo UniLocal",
            modifier = Modifier
                .size(100.dp)  // puedes agrandar sin que se mueva
        )
    }

}
