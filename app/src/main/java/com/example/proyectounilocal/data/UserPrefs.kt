package com.example.proyectounilocal.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DS_NAME = "user_prefs"
private val Context.dataStore by preferencesDataStore(name = DS_NAME)

object UserPrefs {
    private object Keys {
        val LAST_USERNAME = stringPreferencesKey("last_username")
        val LAST_EMAIL    = stringPreferencesKey("last_email")
        val LAST_NAME     = stringPreferencesKey("last_fullname")
        val LAST_CITY     = stringPreferencesKey("last_city")
        val LAST_PASS     = stringPreferencesKey("last_password") // 👈 NUEVO (solo demo)
    }

    // Guarda también la contraseña en claro (solo para el demo local)
    suspend fun saveLastRegistered(
        context: Context,
        username: String,
        email: String,
        fullName: String,
        city: String,
        password: String // 👈 nuevo
    ) {
        context.dataStore.edit { p ->
            p[Keys.LAST_USERNAME] = username
            p[Keys.LAST_EMAIL]    = email
            p[Keys.LAST_NAME]     = fullName
            p[Keys.LAST_CITY]     = city
            p[Keys.LAST_PASS]     = password // 👈 nuevo
        }
    }

    fun lastUsername(context: Context): Flow<String> =
        context.dataStore.data.map { it[Keys.LAST_USERNAME] ?: "" }

    fun lastPassword(context: Context): Flow<String> =                 // 👈 NUEVO
        context.dataStore.data.map { it[Keys.LAST_PASS] ?: "" }
}
