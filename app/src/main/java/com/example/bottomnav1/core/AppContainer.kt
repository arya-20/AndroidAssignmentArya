package com.example.bottomnav1.core

import com.example.bottomnav1.data.auth.AuthRepo
import com.example.bottomnav1.data.auth.AuthRepository
import com.example.bottomnav1.data.contact1.ContactDAO
import com.example.bottomnav1.data.contact1.ContactRepository
import com.example.bottomnav1.data.recipe1.RecipeDAO
import com.example.bottomnav1.data.recipe1.RecipeRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

private const val CONTACT_ROOT_FOLDER = "contacts"
private const val RECIPE_ROOT_FOLDER = "recipes"
private const val DATABASE_URL ="https://cloud-2-5cc69-default-rtdb.europe-west1.firebasedatabase.app/"

interface AppContainer {
    val contactRepository: ContactRepository
    val authRepository: AuthRepo
    val recipeRepository: RecipeRepository
}

class AppDataContainer : AppContainer {
    override val contactRepository: ContactRepository
    override val recipeRepository: RecipeRepository
    override var authRepository: AuthRepo = AuthRepository(FirebaseAuth.getInstance())

    init {
        val contactRoot = FirebaseDatabase.getInstance(DATABASE_URL).getReference(CONTACT_ROOT_FOLDER)
        val contactDAO = ContactDAO(contactRoot)
        contactRepository = ContactRepository(contactDAO)

        val recipeRoot = FirebaseDatabase.getInstance(DATABASE_URL).getReference(RECIPE_ROOT_FOLDER)
        val recipeDAO = RecipeDAO(recipeRoot)
        recipeRepository = RecipeRepository(recipeDAO)
    }
}