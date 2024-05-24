package com.example.bottomnav1.presentation.screens.settings

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    @Composable
    fun isLightModeEnabled(lightTheme: Boolean): Boolean {
        return lightTheme
    }

    fun isNotificationsEnabled(): Boolean {
        return false
    }
}