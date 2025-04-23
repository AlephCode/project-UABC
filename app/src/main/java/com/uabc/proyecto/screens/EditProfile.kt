package com.uabc.proyecto.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.uabc.proyecto.themeswitcher.AppTheme
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
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

    // Estados para diálogos
    var showDatePicker by remember { mutableStateOf(false) }
    var showLocationPicker by remember { mutableStateOf(false) }
    var showProfilePictureOptions by remember { mutableStateOf(false) }

    // Estado para el DatePicker
    val datePickerState = rememberDatePickerState()

    // Formato para mostrar la fecha
    val dateFormatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy")

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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
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
            // Foto de perfil
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.tertiaryContainer)
                            .clickable { showProfilePictureOptions = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Foto de perfil",
                            tint = MaterialTheme.colorScheme.onTertiaryContainer,
                            modifier = Modifier.size(60.dp)
                        )
                    }

                    TextButton(
                        onClick = { showProfilePictureOptions = true }
                    ) {
                        Text("Cambiar foto")
                    }
                }
            }

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
                    leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = "Ubicación") },
                    trailingIcon = {
                        IconButton(onClick = { showLocationPicker = true }) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = "Seleccionar ubicación"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true
                )
            }

            item {
                OutlinedTextField(
                    value = editableBirthDate,
                    onValueChange = { },
                    label = { Text("Fecha de nacimiento") },
                    leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Fecha") },
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker = true }) {
                            Icon(
                                Icons.Default.Edit,
                                contentDescription = "Seleccionar fecha"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true
                )
            }

            item {
                OutlinedTextField(
                    value = editableAboutMe,
                    onValueChange = { editableAboutMe = it },
                    label = { Text("Acerca de mí") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
            }

            item {
                Text(
                    "Intereses",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Lista de intereses con opción para eliminar
            itemsIndexed(editableInterests) { index, interest ->
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
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = {
                            if (editableInterests.size > 3) {
                                editableInterests = editableInterests.toMutableList().apply {
                                    removeAt(index)
                                }
                            }
                        },
                        enabled = editableInterests.size > 3
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar interés",
                            tint = if (editableInterests.size > 3) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                        )
                    }
                }
            }

            // Botón para agregar nuevos intereses
            item {
                if (editableInterests.size < 6) {
                    Button(
                        onClick = { editableInterests = editableInterests + "" },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Agregar")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Agregar interés")
                    }
                } else {
                    Text(
                        "Has alcanzado el máximo de intereses (6).",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Mensaje informativo sobre eliminar intereses
            item {
                if (editableInterests.size <= 3) {
                    Text(
                        "Debes mantener al menos 3 intereses.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        // DatePicker para la selección de fecha de nacimiento
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            // Obtener la fecha seleccionada y formatearla
                            datePickerState.selectedDateMillis?.let { milliseconds ->
                                val date = Instant.ofEpochMilli(milliseconds)
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()
                                editableBirthDate = date.format(dateFormatter)
                            }
                            showDatePicker = false
                        }
                    ) {
                        Text("Aceptar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text("Cancelar")
                    }
                },
                colors = DatePickerDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    headlineContentColor = MaterialTheme.colorScheme.onSurface,
                    weekdayContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    subheadContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    yearContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    currentYearContentColor = MaterialTheme.colorScheme.primary,
                    selectedYearContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedYearContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    dayContentColor = MaterialTheme.colorScheme.onSurface,
                    selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                    selectedDayContentColor = MaterialTheme.colorScheme.onPrimary,
                    todayContentColor = MaterialTheme.colorScheme.primary,
                    todayDateBorderColor = MaterialTheme.colorScheme.primary
                )
            ) {
                DatePicker(
                    state = datePickerState,
                    showModeToggle = true,
                    colors = DatePickerDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                        headlineContentColor = MaterialTheme.colorScheme.onSurface,
                        weekdayContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        subheadContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        yearContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        currentYearContentColor = MaterialTheme.colorScheme.primary,
                        selectedYearContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedYearContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        dayContentColor = MaterialTheme.colorScheme.onSurface,
                        selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                        selectedDayContentColor = MaterialTheme.colorScheme.onPrimary,
                        todayContentColor = MaterialTheme.colorScheme.primary,
                        todayDateBorderColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }

        // Diálogo de selección de ubicación
        if (showLocationPicker) {
            AlertDialog(
                onDismissRequest = { showLocationPicker = false },
                title = { Text("Seleccionar ubicación") },
                text = {
                    Column {
                        Text("Selecciona una ubicación predefinida o introduce una dirección manualmente")

                        Spacer(modifier = Modifier.height(16.dp))

                        // Lista de ubicaciones predefinidas
                        val predefinedLocations = listOf(
                            "Tijuana, Baja California, México",
                            "Mexicali, Baja California, México",
                            "Ensenada, Baja California, México",
                            "Tecate, Baja California, México",
                            "Rosarito, Baja California, México"
                        )

                        Column {
                            predefinedLocations.forEach { locationOption ->
                                Surface(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            editableLocation = locationOption
                                            showLocationPicker = false
                                        },
                                    color = MaterialTheme.colorScheme.surfaceVariant
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .padding(vertical = 12.dp, horizontal = 16.dp)
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            Icons.Default.LocationOn,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Text(locationOption)
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Campo para ingresar manualmente
                        OutlinedTextField(
                            value = editableLocation,
                            onValueChange = { editableLocation = it },
                            label = { Text("Ubicación personalizada") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text(
                            "Nota: Para una implementación completa, se requeriría integrar la API de Google Maps o Places",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showLocationPicker = false }) {
                        Text("Aceptar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showLocationPicker = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }

        // Diálogo para cambiar la foto de perfil
        if (showProfilePictureOptions) {
            AlertDialog(
                onDismissRequest = { showProfilePictureOptions = false },
                title = { Text("Cambiar foto de perfil") },
                text = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Selecciona una opción:")

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                // Aquí iría la lógica para seleccionar de galería
                                showProfilePictureOptions = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.Photo, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Seleccionar de la galería")
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                // Aquí iría la lógica para tomar una foto
                                showProfilePictureOptions = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.PhotoCamera, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Tomar una foto")
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedButton(
                            onClick = { showProfilePictureOptions = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Cancelar")
                        }
                    }
                },
                confirmButton = { },
                dismissButton = { }
            )
        }
    }
}