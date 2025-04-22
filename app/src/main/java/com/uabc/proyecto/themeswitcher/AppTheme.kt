package com.uabc.proyecto.themeswitcher

sealed class AppTheme(val name: String) {
    object SanValentin : AppTheme("SanValentin")
    object Independencia : AppTheme("Independencia")
    object Halloween : AppTheme("Halloween")

    companion object {
        fun fromName(name: String?): AppTheme {
            return when (name) {
                SanValentin.name -> SanValentin
                Independencia.name -> Independencia
                Halloween.name -> Halloween
                else -> Independencia
            }
        }
    }
}
