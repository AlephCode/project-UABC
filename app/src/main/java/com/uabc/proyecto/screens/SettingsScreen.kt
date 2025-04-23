package com.uabc.proyecto.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uabc.proyecto.themeswitcher.AppTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import com.uabc.proyecto.ScaledText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    currentTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit,
    onBack: () -> Unit,
    fontScale: Float,
    onFontScaleChange: (Float) -> Unit
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
                title = { ScaledText("Ajustes") },
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
            ScaledText("Selecciona un tema:", style = MaterialTheme.typography.titleMedium)

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
                    ScaledText(themeLabels[option] ?: "Tema")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Tamaño de Letra",
                modifier = Modifier.padding(8.dp),
                fontSize = MaterialTheme.typography.bodyLarge.fontSize * fontScale
            )

            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = { if (fontScale > 0.5f) onFontScaleChange(fontScale - 0.1f) }) {
                    Text("-")
                }
                Button(onClick = { if (fontScale < 2.0f) onFontScaleChange(fontScale + 0.1f) }) {
                    Text("+")
                }
            }
        }
    }
}

