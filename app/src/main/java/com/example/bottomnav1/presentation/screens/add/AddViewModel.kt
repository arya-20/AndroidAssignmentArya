package com.example.bottomnav1.presentation.screens.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bottomnav1.core.ContactApplication
import com.example.bottomnav1.data.auth.AuthRepo
import com.example.bottomnav1.data.contact1.Contact
import com.example.bottomnav1.data.contact1.ContactRepo
import com.example.bottomnav1.data.recipe1.Recipe
import kotlinx.coroutines.launch

class AddViewModel (private val authRepo: AuthRepo, private val repo: ContactRepo) : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var recipes by mutableStateOf<Recipe?>(null)

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        val passwordRegex = Regex("^[A-Z][a-z]{5,}\$")
        return passwordRegex.matches(password)
    }

    fun addContact() {
        if (isPasswordValid(password) && isEmailValid(email)) {
            val newContact = Contact(
                email = email,
                password = password,
                recipe = null
            )
            viewModelScope.launch {
                repo.add(newContact, authRepo.currentUser!!.uid)
            }
            clearFields()
        }
    }

    private fun clearFields() {
        email = ""
        password = ""
        recipes = null   }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AddViewModel(
                    authRepo = ContactApplication.container.authRepository,
                    repo = ContactApplication.container.contactRepository
                )
            }
        }

    }
}