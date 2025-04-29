package com.uabc.proyecto.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.res.stringResource
import com.uabc.proyecto.R
import com.uabc.proyecto.themeswitcher.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    windowSizeClass: WindowSizeClass,
    currentTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit,
    onBack: () -> Unit
) {
    var windowSizeClass = windowSizeClass
    print(windowSizeClass.widthSizeClass.toString())
    print(windowSizeClass.heightSizeClass.toString())

    val themeOptions = listOf(
        AppTheme.SanValentin,
        AppTheme.Independencia,
        AppTheme.Halloween
    )

    val themeLabels = mapOf(
        AppTheme.SanValentin to stringResource(id = R.string.sanValetin),
        AppTheme.Independencia to stringResource(id = R.string.independenciaMexico),
        AppTheme.Halloween to stringResource(id = R.string.halloween)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.ajustes)) },
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
            Text(stringResource(id = R.string.selectTema), style = MaterialTheme.typography.titleMedium)

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
                    Text(themeLabels[option] ?: stringResource(id = R.string.tema))
                }
            }
        }
    }
}
