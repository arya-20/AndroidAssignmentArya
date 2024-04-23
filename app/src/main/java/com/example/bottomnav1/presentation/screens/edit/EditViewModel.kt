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

class EditViewModel (private val authRepo: AuthRepo, private val repo: ContactRepo) : ViewModel() {
    private var selectedContact : Contact? = null

    var id by mutableStateOf(String())
    var firstName by mutableStateOf(String())
    var surname by mutableStateOf(String())
    var telNo by mutableStateOf(String())

    fun setSelectedContact(contact: Contact){
        id = contact.id.toString()
        firstName = contact.firstName.toString()
        surname = contact.surname.toString()
        telNo = contact.telNo.toString()
        selectedContact = contact
    }

    fun firstNameIsValid():Boolean{
        return firstName.isNotBlank()
    }

    fun surnameIsValid():Boolean{
        return surname.isNotBlank()
    }

    fun telNoIsValid():Boolean{
        return telNo.isNotBlank()
    }

    fun updateContact(){
        if (selectedContact!=null
            && firstNameIsValid()
            && surnameIsValid()
            && telNoIsValid())  {
            selectedContact!!.firstName = firstName
            selectedContact!!.surname = surname
            selectedContact!!.telNo = telNo
            repo.edit(selectedContact!!, authRepo.currentUser!!.uid)
        }
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory= viewModelFactory() {
            initializer {
                EditViewModel(
                    authRepo = ContactApplication.container.authRepository,
                    repo = ContactApplication.container.contactRepository
                )
            }
        }
    }
}