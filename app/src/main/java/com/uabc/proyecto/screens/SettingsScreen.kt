package com.uabc.proyecto.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uabc.proyecto.themeswitcher.AppTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    currentTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit,
    onBack: () -> Unit
) {
    val themeOptions = listOf(
        AppTheme.SanValentin,
        AppTheme.Independencia,
        AppTheme.Halloween
    )

    val themeLabels = mapOf(
        AppTheme.SanValentin to "San Valentín 💖",
        AppTheme.Independencia to "Independencia 🇲🇽",
        AppTheme.Halloween to "Halloween 🎃"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ajustes") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Selecciona un tema:", style = MaterialTheme.typography.titleMedium)

            themeOptions.forEach { option ->
                Button(
                    onClick = { onThemeSelected(option) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (option == currentTheme)
                            MaterialTheme.colorScheme.secondary
                        else
                            MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(themeLabels[option] ?: "Tema")
                }
            }
        }
    }
}
