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

@Composable
fun ThemedApp() {
    val isDark = isSystemInDarkTheme()
    val viewModel: ThemeViewModel = viewModel()
    val currentTheme = viewModel.currentTheme.collectAsState().value

    if (currentTheme == null) {
        Box(modifier = Modifier.fillMaxSize())
        return
    }

    val colorScheme = when (currentTheme) {
        is AppTheme.SanValentin -> if (isDark) darkSanValentinScheme else lightSanValentinScheme
        is AppTheme.Independencia -> if (isDark) darkIndependenciaScheme else lightIndependenciaScheme
        is AppTheme.Halloween -> if (isDark) darkHalloweenScheme else lightHalloweenScheme
        else -> if (isDark) darkIndependenciaScheme else lightIndependenciaScheme // fallback
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography()
    ) {
        AppNavigation(
            navController = rememberNavController(),
            currentTheme = currentTheme,
            onThemeSelected = { viewModel.updateTheme(it) }
        )
    }
}
