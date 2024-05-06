package com.example.bottomnav1.presentation.screens.settings

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    @Composable
    fun isDarkModeEnabled(): Boolean {
        return isSystemInDarkTheme()
    }
}