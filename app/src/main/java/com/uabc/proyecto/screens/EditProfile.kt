package com.uabc.proyecto.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.res.stringResource
import com.uabc.proyecto.R
import com.uabc.proyecto.themeswitcher.AppTheme
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile(
    windowSizeClass: WindowSizeClass,
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
    var windowSizeClass = windowSizeClass
    print(windowSizeClass.widthSizeClass.toString())
    print(windowSizeClass.heightSizeClass.toString())


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

    // Obtener configuración de pantalla para adaptación
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val isLandscape = screenWidth > screenHeight

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.editarPerfil)) },
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
                        Text(stringResource(id = R.string.cambiarFoto))
                    }
                }
            }

            item {
                OutlinedTextField(
                    value = editableName,
                    onValueChange = { editableName = it },
                    label = { Text(stringResource(id = R.string.nombre)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = editableEmail,
                    onValueChange = { editableEmail = it },
                    label = { Text(stringResource(id = R.string.correoElectronico)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = editableLocation,
                    onValueChange = { editableLocation = it },
                    label = { Text(stringResource(id = R.string.ubicacion)) },
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
                    label = { Text(stringResource(id = R.string.fechaNacimiento)) },
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
                    label = { Text(stringResource(id = R.string.acerca)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
            }

            item {
                Text(
                    stringResource(id = R.string.interes),
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
                        label = { Text(stringResource(id = R.string.interes) + "${index + 1}") },
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
                            tint = if (editableInterests.size > 3) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.error.copy(alpha = 0.35f)
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
                        Text(stringResource(id = R.string.addInteres))
                    }
                } else {
                    Text(
                        stringResource(id = R.string.maxInteres),
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
                        stringResource(id = R.string.minInteres),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

// DatePicker para la selección de fecha de nacimiento - Adaptable a la orientación
        if (showDatePicker) {
            Dialog(
                onDismissRequest = { showDatePicker = false },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                    usePlatformDefaultWidth = false // Permite que el diálogo se ajuste al contenido
                )
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(if (isLandscape) 0.85f else 0.95f)
                        // Reducir la altura máxima en modo horizontal para ver todos los días
                        .fillMaxHeight(if (isLandscape) 0.80f else 0.7f),
                    shape = MaterialTheme.shapes.extraLarge,
                    tonalElevation = 6.dp
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Título y botones - hacer más compacto
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 24.dp,
                                    end = 24.dp,
                                    top = if (isLandscape) 12.dp else 20.dp,
                                    bottom = if (isLandscape) 8.dp else 12.dp
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                stringResource(id = R.string.selectFecha),
                                style = if (isLandscape)
                                    MaterialTheme.typography.titleMedium
                                else
                                    MaterialTheme.typography.titleLarge
                            )

                            Row {
                                TextButton(onClick = { showDatePicker = false }) {
                                    Text(stringResource(id = R.string.cancel))
                                }
                                TextButton(
                                    onClick = {
                                        datePickerState.selectedDateMillis?.let { milliseconds ->
                                            val date = Instant.ofEpochMilli(milliseconds)
                                                .atZone(ZoneId.systemDefault())
                                                .toLocalDate()
                                            editableBirthDate = date.format(dateFormatter)
                                        }
                                        showDatePicker = false
                                    }
                                ) {
                                    Text(stringResource(id = R.string.accept))
                                }
                            }
                        }

                        // Contenido del DatePicker adaptado a la orientación - más compacto en horizontal
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    horizontal = 12.dp,
                                    vertical = if (isLandscape) 4.dp else 8.dp
                                )
                        ) {
                            DatePicker(
                                state = datePickerState,
                                modifier = Modifier.fillMaxSize(),
                                // Solo mostrar el modo compacto en horizontal para ahorrar espacio
                                showModeToggle = !isLandscape,
                                title = null, // Ya tenemos un título personalizado arriba
                                headline = null, // Para ahorrar espacio
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
                }
            }
        }

        // Diálogo de selección de ubicación - Adaptable a la orientación
        if (showLocationPicker) {
            Dialog(
                onDismissRequest = { showLocationPicker = false },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                    usePlatformDefaultWidth = false
                )
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(if (isLandscape) 0.85f else 0.95f)
                        .fillMaxHeight(if (isLandscape) 0.95f else 0.75f),
                    shape = MaterialTheme.shapes.extraLarge,
                    tonalElevation = 6.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 16.dp, horizontal = 24.dp)
                    ) {
                        // Título
                        Text(
                            stringResource(id = R.string.selectUbicacion),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Contenido con scroll adaptable
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .verticalScroll(rememberScrollState())
                        ) {
                            Text(stringResource(id = R.string.setUbicacion))

                            Spacer(modifier = Modifier.height(16.dp))

                            // Lista de ubicaciones predefinidas
                            val predefinedLocations = listOf(
                                "Tijuana, Baja California, México",
                                "Mexicali, Baja California, México",
                                "Ensenada, Baja California, México",
                                "Tecate, Baja California, México",
                                "Rosarito, Baja California, México"
                            )

                            // Layout adaptable según orientación
                            if (isLandscape && screenWidth >= 600.dp) {
                                // Diseño para pantallas grandes en horizontal
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    // Columna de ubicaciones predefinidas
                                    Column(
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text(
                                            stringResource(id = R.string.ubicacionPredefinida),
                                            style = MaterialTheme.typography.titleSmall,
                                            modifier = Modifier.padding(bottom = 8.dp)
                                        )

                                        predefinedLocations.forEach { locationOption ->
                                            Surface(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .clickable {
                                                        editableLocation = locationOption
                                                        showLocationPicker = false
                                                    },
                                                color = MaterialTheme.colorScheme.surfaceVariant,
                                                shape = MaterialTheme.shapes.small
                                            ) {
                                                Row(
                                                    modifier = Modifier
                                                        .padding(vertical = 10.dp, horizontal = 12.dp)
                                                        .fillMaxWidth(),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Icon(
                                                        Icons.Default.LocationOn,
                                                        contentDescription = null,
                                                        tint = MaterialTheme.colorScheme.primary,
                                                        modifier = Modifier.size(18.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(8.dp))
                                                    Text(locationOption)
                                                }
                                            }
                                            Spacer(modifier = Modifier.height(6.dp))
                                        }
                                    }

                                    // Columna para ubicación personalizada
                                    Column(
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text(
                                            stringResource(id = R.string.ubicacionPersonalizada),
                                            style = MaterialTheme.typography.titleSmall,
                                            modifier = Modifier.padding(bottom = 8.dp)
                                        )

                                        OutlinedTextField(
                                            value = editableLocation,
                                            onValueChange = { editableLocation = it },
                                            label = { Text(stringResource(id = R.string.insertarUbicacion)) },
                                            leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) },
                                            modifier = Modifier.fillMaxWidth()
                                        )

                                        Text(
                                            stringResource(id = R.string.notaUbicacion),
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            modifier = Modifier.padding(top = 8.dp)
                                        )
                                    }
                                }
                            } else {
                                // Diseño para pantallas más pequeñas o en vertical
                                Column {
                                    Text(
                                        stringResource(id = R.string.ubicacionPredefinida),
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )

                                    predefinedLocations.forEach { locationOption ->
                                        Surface(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable {
                                                    editableLocation = locationOption
                                                    showLocationPicker = false
                                                },
                                            color = MaterialTheme.colorScheme.surfaceVariant,
                                            shape = MaterialTheme.shapes.small
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

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Text(
                                        stringResource(id = R.string.ubicacionPersonalizada),
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )

                                    OutlinedTextField(
                                        value = editableLocation,
                                        onValueChange = { editableLocation = it },
                                        label = { Text(stringResource(id = R.string.insertarUbicacion)) },
                                        leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) },
                                        modifier = Modifier.fillMaxWidth()
                                    )

                                    Text(
                                        stringResource(id = R.string.notaUbicacion),
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.padding(top = 8.dp)
                                    )
                                }
                            }
                        }

                        // Botones de acción
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)
                        ) {
                            TextButton(onClick = { showLocationPicker = false }) {
                                Text(stringResource(id = R.string.cancel))
                            }

                            Button(onClick = { showLocationPicker = false }) {
                                Text(stringResource(id = R.string.accept))
                            }
                        }
                    }
                }
            }
        }

        // Diálogo para cambiar la foto de perfil
        if (showProfilePictureOptions) {
            AlertDialog(
                onDismissRequest = { showProfilePictureOptions = false },
                title = { Text(stringResource(id = R.string.cambiarFotoPerfil)) },
                text = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(stringResource(id = R.string.selectOpcion))

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
                            Text(stringResource(id = R.string.selectGaleria))
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
                            Text(stringResource(id = R.string.selectFoto))
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedButton(
                            onClick = { showProfilePictureOptions = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(stringResource(id = R.string.cancel))
                        }
                    }
                },
                confirmButton = { },
                dismissButton = { }
            )
        }
    }
}