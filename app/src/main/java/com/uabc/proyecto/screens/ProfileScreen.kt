package com.uabc.proyecto.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ExitToApp

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.uabc.proyecto.themeswitcher.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    currentTheme: AppTheme,
    onNavigateToSettings: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil") },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Ajustes")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("Aleph Lau", style = MaterialTheme.typography.titleLarge)
                Text("aleph@correo.com", color = MaterialTheme.colorScheme.onSurfaceVariant)

                Spacer(modifier = Modifier.height(16.dp))

                Divider(color = MaterialTheme.colorScheme.outline)

                Spacer(modifier = Modifier.height(16.dp))

                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Acerca de mí", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Desarrollador apasionado por el diseño limpio, el código mantenible y los temas oscuros.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text("Intereses", style = MaterialTheme.typography.titleMedium)

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    AssistChip(
                        onClick = { },
                        label = { Text("Kotlin") }
                    )
                    AssistChip(
                        onClick = { },
                        label = { Text("Android") }
                    )
                    AssistChip(
                        onClick = { },
                        label = { Text("UI/UX") }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text("Información personal", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = "Tijuana, México",
                    onValueChange = {},
                    label = { Text("Ubicación") },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = "15 de Febrero de 1995",
                    onValueChange = {},
                    label = { Text("Fecha de nacimiento") },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                FilledTonalButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                    Spacer(Modifier.width(8.dp))
                    Text("Editar perfil")
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Filled.ExitToApp, contentDescription = "Cerrar sesión")
                    Spacer(Modifier.width(8.dp))
                    Text("Cerrar sesión")
                }
            }
        }
    }
}
