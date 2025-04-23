package com.uabc.proyecto

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.uabc.proyecto.navigation.AppNavigation
import com.uabc.proyecto.themeswitcher.*
import androidx.compose.runtime.compositionLocalOf


val LocalFontScale = compositionLocalOf { 1.0f }

@Composable
fun ThemedApp() {
    val isDark = isSystemInDarkTheme()
    val viewModel: ThemeViewModel = viewModel()
    val currentTheme = viewModel.currentTheme.collectAsState().value

    if (currentTheme == null) {
        Box(modifier = Modifier.fillMaxSize())
        return
    }

    var fontScale by remember { mutableStateOf(1.0f) }

    CompositionLocalProvider(
        *arrayOf(LocalFontScale provides fontScale)
    ) {
        val colorScheme = when (currentTheme) {
            is AppTheme.SanValentin -> if (isDark) darkSanValentinScheme else lightSanValentinScheme
            is AppTheme.Independencia -> if (isDark) darkIndependenciaScheme else lightIndependenciaScheme
            is AppTheme.Halloween -> if (isDark) darkHalloweenScheme else lightHalloweenScheme
        }

        MaterialTheme(
            colorScheme = colorScheme,
        ) {
            val navController = rememberNavController()
            AppNavigation(
                navController = navController,
                currentTheme = currentTheme,
                onThemeSelected = { viewModel.setTheme(it) },
                fontScale = fontScale,
                onFontScaleChange = { newScale -> fontScale = newScale }
            )
        }
    }
}

