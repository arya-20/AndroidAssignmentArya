package com.example.bottomnav1.core

import com.example.bottomnav1.data.auth.AuthRepo
import com.example.bottomnav1.data.auth.AuthRepository
import com.example.bottomnav1.data.contact1.ContactDAO
import com.example.bottomnav1.data.contact1.ContactRepo
import com.example.bottomnav1.data.contact1.ContactRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

private const val CONTACT_ROOT_FOLDER = "contacts"
private const val DATABASE_URL ="https://cloud1-1e322-default-rtdb.europe-west1.firebasedatabase.app/"

interface AppContainer {
    val contactRepository: ContactRepo
    val authRepository: AuthRepo
}

class AppDataContainer : AppContainer {
    override val contactRepository: ContactRepo
    override var authRepository: AuthRepo = AuthRepository(FirebaseAuth.getInstance())

    init {
        val contactRoot = FirebaseDatabase.getInstance(DATABASE_URL).getReference(CONTACT_ROOT_FOLDER)
        val contactDAO = ContactDAO(contactRoot)
        contactRepository = ContactRepository(contactDAO)
    }
}