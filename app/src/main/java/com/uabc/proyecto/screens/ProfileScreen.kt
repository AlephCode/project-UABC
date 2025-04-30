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
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.uabc.proyecto.themeswitcher.AppTheme
import com.uabc.proyecto.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    windowSizeClass: WindowSizeClass,
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
    var windowSizeClass = windowSizeClass
    //val isExpanded = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded &&
            //windowSizeClass.heightSizeClass != WindowHeightSizeClass.Compact

    //val isHorizontal = windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.miPerfil)) },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Ajustes")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded &&
            windowSizeClass.heightSizeClass == WindowHeightSizeClass.Medium) {
            // Vista en dos columnas
            Row(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    item { ProfileHeader(32, name, email, windowSizeClass.widthSizeClass.toString(),
                        windowSizeClass.heightSizeClass.toString()) }
                    item { AboutMeSection(28, aboutMe) }
                    item { InterestsSection(interests) }
                }


                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        PersonalInfoSection(32, location, birthDate)
                    }
                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                        ProfileActions(28, onNavigateToEditProfile)
                    }
                }

            }
        } else {
            if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium &&
                windowSizeClass.heightSizeClass == WindowHeightSizeClass.Expanded){
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item { ProfileHeader(40, name, email, windowSizeClass.widthSizeClass.toString(),
                        windowSizeClass.heightSizeClass.toString()) }
                    item { AboutMeSection(36, aboutMe) }
                    item { InterestsSection(interests) }
                    item { PersonalInfoSection(40, location, birthDate) }
                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                        ProfileActions(36, onNavigateToEditProfile)
                    }
                }
            } else {
                if(windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact) {
                    // Vista en dos columnas
                    Row(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp)
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            item { ProfileHeader(12, name, email, windowSizeClass.widthSizeClass.toString(),
                                windowSizeClass.heightSizeClass.toString()) }
                            item { AboutMeSection(8, aboutMe) }
                            item { InterestsSection(interests) }
                        }


                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            item {
                                PersonalInfoSection(12, location, birthDate)
                            }
                            item {
                                Spacer(modifier = Modifier.height(24.dp))
                                ProfileActions(8, onNavigateToEditProfile)
                            }
                        }

                    }
                }else {
                        // Vista vertical única
                        LazyColumn(
                            modifier = Modifier
                                .padding(innerPadding)
                                .padding(16.dp)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            item { ProfileHeader(12, name, email, windowSizeClass.widthSizeClass.toString(),
                                windowSizeClass.heightSizeClass.toString()) }
                            item { AboutMeSection(8, aboutMe) }
                            item { InterestsSection(interests) }
                            item { PersonalInfoSection(12, location, birthDate) }
                            item {
                                Spacer(modifier = Modifier.height(24.dp))
                                ProfileActions(8, onNavigateToEditProfile)
                            }
                        }
                    }
                }
            }
        }
                }





@Composable
fun ProfileHeader(spacer: Int, name: String, email: String, width: String, height: String) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.tertiaryContainer)
    )
    Spacer(modifier = Modifier.height(spacer.dp))
    //Text(width)
    //Text(height)
    Text(name, style = MaterialTheme.typography.titleLarge)
    Text(email, color = MaterialTheme.colorScheme.onSurfaceVariant)
    Spacer(modifier = Modifier.height((spacer+4).dp))
    Divider(color = MaterialTheme.colorScheme.outline)
    Spacer(modifier = Modifier.height((spacer+4).dp))
}

@Composable
fun AboutMeSection(spacer: Int, aboutMe: String) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(stringResource(id = R.string.acerca),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(spacer.dp))
            Text(aboutMe,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
    Spacer(modifier = Modifier.height((spacer+16).dp))
}

@Composable
fun InterestsSection(interests: List<String>) {
    Text(stringResource(id = R.string.intereses), style = MaterialTheme.typography.titleMedium)
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = 8.dp)
    ) {
        items(interests) { interest ->
            AssistChip(
                onClick = { },
                label = { Text(interest) }
            )
        }
    }
    Spacer(modifier = Modifier.height(26.dp))
}

@Composable
fun PersonalInfoSection(spacer: Int, location: String, birthDate: String) {
    Text(stringResource(id = R.string.informacionPersonal), style = MaterialTheme.typography.titleMedium)
    Spacer(modifier = Modifier.height(spacer.dp))
    OutlinedTextField(
        value = location,
        onValueChange = {},
        label = { Text(stringResource(id = R.string.ubicacion)) },
        enabled = false,
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = MaterialTheme.colorScheme.onSurface, // Color cuando está deshabilitado
            disabledTextColor = MaterialTheme.colorScheme.onSurface,    // Color del texto cuando está deshabilitado
            disabledLabelColor = MaterialTheme.colorScheme.onSurface    // Color del label cuando está deshabilitado
        )
    )
    Spacer(modifier = Modifier.height((spacer-4).dp))
    OutlinedTextField(
        value = birthDate,
        onValueChange = {},
        label = { Text(stringResource(id = R.string.fechaNacimiento)) },
        enabled = false,
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = MaterialTheme.colorScheme.onSurface, // Color cuando está deshabilitado
            disabledTextColor = MaterialTheme.colorScheme.onSurface,    // Color del texto cuando está deshabilitado
            disabledLabelColor = MaterialTheme.colorScheme.onSurface    // Color del label cuando está deshabilitado
        )

    )
}

@Composable
fun ProfileActions(spacer: Int, onNavigateToEditProfile: () -> Unit) {
    FilledTonalButton(
        onClick = { onNavigateToEditProfile() },
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(Icons.Default.Edit, contentDescription = "Editar")
        Spacer(Modifier.width(spacer.dp))
        Text(text = stringResource(id = R.string.editar))
    }

    Spacer(modifier = Modifier.height((spacer+4).dp))

    OutlinedButton(
        onClick = { /* logout action */ },
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(Icons.Filled.ExitToApp, contentDescription = "Cerrar sesión")
        Spacer(Modifier.width(8.dp))
        Text(text = stringResource(id = R.string.cerrar))
    }
}

