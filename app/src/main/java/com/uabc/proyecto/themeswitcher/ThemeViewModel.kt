package com.uabc.proyecto.themeswitcher

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ThemeViewModel(application: Application) : AndroidViewModel(application) {
    private val preference = ThemePreference(application)

    private val _currentTheme = MutableStateFlow<AppTheme?>(null)
    val currentTheme: StateFlow<AppTheme?> = _currentTheme.asStateFlow()

    init {
        viewModelScope.launch {
            preference.getTheme().collect { savedTheme ->
                _currentTheme.value = savedTheme
            }
        }
    }

    fun updateTheme(theme: AppTheme) {
        _currentTheme.value = theme
        viewModelScope.launch {
            preference.saveTheme(theme)
        }
    }

    fun setTheme(theme: AppTheme) {
        _currentTheme.value = theme
    }

}
