package com.uabc.proyecto.themeswitcher

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "theme_settings")

class ThemePreference(private val context: Context) {
    companion object {
        val THEME_KEY = stringPreferencesKey("selected_theme")
    }

    fun getTheme(): Flow<AppTheme> {
        return context.dataStore.data.map { prefs ->
            AppTheme.fromName(prefs[THEME_KEY])
        }
    }

    suspend fun saveTheme(theme: AppTheme) {
        context.dataStore.edit { prefs ->
            prefs[THEME_KEY] = theme.name
        }
    }
}
