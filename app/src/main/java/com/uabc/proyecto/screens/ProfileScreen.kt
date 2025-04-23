package com.uabc.proyecto.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.uabc.proyecto.themeswitcher.AppTheme

import com.uabc.proyecto.ScaledText


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    currentTheme: AppTheme,
    name: String,
    email: String,
    location: String,
    birthDate: String,
    aboutMe: String,
    interests: List<String>,
    onNavigateToSettings: () -> Unit,
    onNavigateToEditProfile: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { ScaledText("Mi Perfil") },
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
                ScaledText(name, style = MaterialTheme.typography.titleLarge)
                ScaledText(email, style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSurfaceVariant))
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
                        ScaledText("Acerca de mí", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        ScaledText(aboutMe, style = MaterialTheme.typography.bodyMedium)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                ScaledText("Intereses", style = MaterialTheme.typography.titleMedium)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    items(interests) { interest ->
                        AssistChip(
                            onClick = { },
                            label = { ScaledText(interest) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(26.dp))
                ScaledText("Información personal", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = location,
                    onValueChange = {},
                    label = { ScaledText("Ubicación") },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = birthDate,
                    onValueChange = {},
                    label = { ScaledText("Fecha de nacimiento") },
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Spacer(modifier = Modifier.height(24.dp))
                FilledTonalButton(
                    onClick = { onNavigateToEditProfile() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                    Spacer(Modifier.width(8.dp))
                    ScaledText("Editar perfil")
                }
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Filled.ExitToApp, contentDescription = "Cerrar sesión")
                    Spacer(Modifier.width(8.dp))
                    ScaledText("Cerrar sesión")
                }
            }
        }
    }
}
