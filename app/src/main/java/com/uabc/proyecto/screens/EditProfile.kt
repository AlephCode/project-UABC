package com.uabc.proyecto.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.uabc.proyecto.themeswitcher.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(
    currentTheme: AppTheme,
    name: String,
    email: String,
    location: String,
    birthDate: String,
    aboutMe: String,
    interests: List<String>,
    onNavigateBack: () -> Unit,
    onSaveProfile: (String, String, String, String, String, List<String>) -> Unit
) {
    var editableName by remember { mutableStateOf(name) }
    var editableEmail by remember { mutableStateOf(email) }
    var editableLocation by remember { mutableStateOf(location) }
    var editableBirthDate by remember { mutableStateOf(birthDate) }
    var editableAboutMe by remember { mutableStateOf(aboutMe) }
    var editableInterests by remember { mutableStateOf(interests) }
    var interestToDelete by remember { mutableStateOf(-1) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Perfil") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        onSaveProfile(editableName, editableEmail, editableLocation, editableBirthDate, editableAboutMe, editableInterests)
                    }) {
                        Icon(Icons.Default.Check, contentDescription = "Guardar")
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                OutlinedTextField(
                    value = editableName,
                    onValueChange = { editableName = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                OutlinedTextField(
                    value = editableEmail,
                    onValueChange = { editableEmail = it },
                    label = { Text("Correo Electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                OutlinedTextField(
                    value = editableLocation,
                    onValueChange = { editableLocation = it },
                    label = { Text("Ubicación") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                OutlinedTextField(
                    value = editableBirthDate,
                    onValueChange = { editableBirthDate = it },
                    label = { Text("Fecha de nacimiento") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                OutlinedTextField(
                    value = editableAboutMe,
                    onValueChange = { editableAboutMe = it },
                    label = { Text("Acerca de mí") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Text("Intereses", style = MaterialTheme.typography.titleMedium)
            }
            itemsIndexed(editableInterests) { index, interest ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = interest,
                            onValueChange = { updatedInterest ->
                                editableInterests = editableInterests.toMutableList().apply {
                                    set(index, updatedInterest)
                                }
                            },
                            label = { Text("Interés ${index + 1}") },
                            modifier = Modifier
                                .weight(1f)
                                .pointerInput(Unit) {
                                    if (editableInterests.size > 3) {
                                        detectHorizontalDragGestures { _, dragAmount ->
                                            if (dragAmount > 50) {
                                                interestToDelete = index
                                            }
                                        }
                                    }
                                }
                        )
                        if (interestToDelete == index) {
                            IconButton(
                                onClick = {
                                    if (editableInterests.size > 3) {
                                        editableInterests = editableInterests.toMutableList().apply {
                                            removeAt(index)
                                        }
                                        interestToDelete = -1
                                    }
                                },
                                modifier = Modifier.padding(start = 8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Eliminar",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }
            item {
                if (editableInterests.size < 6) {
                    Button(
                        onClick = {
                            editableInterests = editableInterests + ""
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Agregar interés")
                    }
                } else {
                    Text("Has alcanzado el máximo de intereses.", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
