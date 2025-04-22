package com.uabc.proyecto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.uabc.proyecto.themeswitcher.AppTheme
import com.uabc.proyecto.screens.ProfileScreen
import com.uabc.proyecto.screens.SettingsScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    currentTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit
) {
    NavHost(navController, startDestination = "profile") {
        composable("profile") {
            ProfileScreen(
                currentTheme = currentTheme,
                onNavigateToSettings = { navController.navigate("settings") }
            )
        }
        composable("settings") {
            SettingsScreen(
                currentTheme = currentTheme,
                onThemeSelected = onThemeSelected,
                onBack = { navController.popBackStack() }
            )
        }
    }
}