package com.example.bottomnav1.presentation.screens.settings
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
    private var isDarkModeEnabled = false

    fun toggleDarkMode() {
        isDarkModeEnabled = !isDarkModeEnabled
    }

    fun isDarkModeEnabled(): Boolean {
        return isDarkModeEnabled
    }

    fun saveSettings(preferences: SharedPreferences) {
        preferences.edit().apply {
            putBoolean("darkMode", isDarkModeEnabled)
            apply()
        }
    }

    fun loadSettings(preferences: SharedPreferences) {
        isDarkModeEnabled = preferences.getBoolean("darkMode", false)
    }
}