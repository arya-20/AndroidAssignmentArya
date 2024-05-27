package com.example.bottomnav1.presentation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.bottomnav1.data.auth.AuthRepo
import com.example.bottomnav1.presentation.navigation.NavScreen
import kotlinx.coroutines.launch

class SettingsViewModel(private val authRepo: AuthRepo) : ViewModel() {

    fun isLightModeEnabled(lightTheme: Boolean): Boolean {
        return lightTheme
    }

    fun isNotificationsEnabled(): Boolean {
        return false
    }

    fun deleteAccount(navController: NavHostController) {
        viewModelScope.launch {
            try {
                authRepo.deleteUser()
                navController.navigate(NavScreen.Start.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            } catch (e: Exception) {
            }
        }
    }

    class Factory(private val authRepo: AuthRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SettingsViewModel(authRepo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
