package com.example.bottomnav1.presentation.screens.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bottomnav1.core.ContactApplication
import com.example.bottomnav1.data.auth.AuthRepo
import com.example.bottomnav1.data.contact1.Contact
import com.example.bottomnav1.data.contact1.ContactRepo

class EditViewModel(private val authRepo: AuthRepo, private val repo: ContactRepo) : ViewModel() {
    private var selectedContact: Contact? = null

    var id by mutableStateOf(String())
    var email by mutableStateOf(String())
    var password by mutableStateOf(String())
    var recipe by mutableStateOf(String())

    fun setSelectedContact(contact: Contact) {
        id = contact.id.toString()
        email = contact.email.toString()
        password = contact.password.toString()
        recipe = contact.recipe.toString()
        selectedContact = contact
    }

    fun emailIsValid(): Boolean {
        return email.isNotBlank()
    }

    fun passwordIsValid(): Boolean {
        return password.isNotBlank()
    }

    fun recipeIsValid(): Boolean {
        return recipe.isNotBlank()
    }

    fun updateContact() {
        if (selectedContact != null
            && emailIsValid()
            && passwordIsValid()
            && recipeIsValid()
        ) {
            selectedContact!!.email = email
            selectedContact!!.password = password
            //selectedContact!!.recipe = recipe
            repo.edit(selectedContact!!, authRepo.currentUser!!.uid)
        }
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory() {
            initializer {
                EditViewModel(
                    authRepo = ContactApplication.container.authRepository,
                    repo = ContactApplication.container.contactRepository
                )
            }
        }
    }
}