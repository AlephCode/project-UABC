package com.uabc.proyecto.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.uabc.proyecto.screens.EditProfile
import com.uabc.proyecto.themeswitcher.AppTheme
import com.uabc.proyecto.screens.ProfileScreen
import com.uabc.proyecto.screens.SettingsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    navController: NavHostController,
    currentTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit
) {
    var name by remember { mutableStateOf("Aleph Lau") }
    var email by remember { mutableStateOf("aleph@correo.com") }
    var location by remember { mutableStateOf("Tijuana, México") }
    var birthDate by remember { mutableStateOf("15 de Febrero de 1995") }
    var aboutMe by remember { mutableStateOf("Desarrollador apasionado por el diseño limpio, el código mantenible y los temas oscuros.") }
    var interests by remember { mutableStateOf(listOf("Kotlin", "Android", "UI/UX")) }

    NavHost(navController, startDestination = "profile") {
        composable("profile") {
            ProfileScreen(
                currentTheme = currentTheme,
                name = name,
                email = email,
                location = location,
                birthDate = birthDate,
                aboutMe = aboutMe,
                interests = interests,
                onNavigateToSettings = { navController.navigate("settings") },
                onNavigateToEditProfile = {
                    navController.navigate("edit")
                }
            )
        }
        composable("settings") {
            SettingsScreen(
                currentTheme = currentTheme,
                onThemeSelected = onThemeSelected,
                onBack = { navController.popBackStack() }
            )
        }
        composable("edit") {
            EditProfile(
                currentTheme = currentTheme,
                name = name,
                email = email,
                location = location,
                birthDate = birthDate,
                aboutMe = aboutMe,
                interests = interests,
                onNavigateBack = { navController.popBackStack() },
                onSaveProfile = { updatedName, updatedEmail, updatedLocation, updatedBirthDate, updatedAboutMe, updatedInterests ->
                    name = updatedName
                    email = updatedEmail
                    location = updatedLocation
                    birthDate = updatedBirthDate
                    aboutMe = updatedAboutMe
                    interests = updatedInterests
                    navController.popBackStack()
                }
            )
        }
    }
}
