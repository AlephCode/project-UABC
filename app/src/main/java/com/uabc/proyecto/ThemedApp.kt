package com.uabc.proyecto

import androidx.compose.material3.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.uabc.proyecto.themeswitcher.*
import com.uabc.proyecto.navigation.AppNavigation


@Composable
fun ThemedApp() {
    var currentTheme by remember { mutableStateOf<AppTheme>(AppTheme.Independencia) }

    val isDark = isSystemInDarkTheme()

    val colors: ColorScheme = when (currentTheme) {
        is AppTheme.Independencia -> if (isDark) darkIndependenciaScheme else lightIndependenciaScheme
        is AppTheme.SanValentin -> if (isDark) darkSanValentinScheme else lightSanValentinScheme
        is AppTheme.Halloween -> if (isDark) darkHalloweenScheme else lightHalloweenScheme
    }


    MaterialTheme(
        colorScheme = colors,
        typography = Typography()
    ) {
        AppNavigation(
            navController = rememberNavController(),
            currentTheme = currentTheme,
            onThemeSelected = { currentTheme = it }
        )
    }
}
