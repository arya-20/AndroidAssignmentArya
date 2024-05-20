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

    val isRunningTest: Boolean
}

class AppDataContainer : AppContainer {

    override val isRunningTest : Boolean by lazy {
        try {
            Class.forName("androidx.test.espresso.Espresso")
            true
        } catch (e: ClassNotFoundException) {
            false
        }
    }

    override lateinit var  contactRepository: ContactRepository
    override lateinit var recipeRepository: RecipeRepository
    override lateinit var authRepository: AuthRepo



//    init {
//        val contactRoot = FirebaseDatabase.getInstance(DATABASE_URL).getReference(CONTACT_ROOT_FOLDER)
//        val contactDAO = ContactDAO(contactRoot)
//        contactRepository = ContactRepository(contactDAO)
//
//        val recipeRoot = FirebaseDatabase.getInstance(DATABASE_URL).getReference(RECIPE_ROOT_FOLDER)
//        val recipeDAO = RecipeDAO(recipeRoot)
//        recipeRepository = RecipeRepository(recipeDAO)
//
//    }

    init {
        val APPENDED_TEST_PATH = if (isRunningTest) "test" else String()

        val contactRoot = FirebaseDatabase.getInstance(DATABASE_URL).getReference("$APPENDED_TEST_PATH $CONTACT_ROOT_FOLDER")
        val ContactDAO = ContactDAO(contactRoot)
        contactRepository = ContactRepository(ContactDAO)

        val recipeRoot = FirebaseDatabase.getInstance(DATABASE_URL).getReference("$APPENDED_TEST_PATH $RECIPE_ROOT_FOLDER")
        recipeRepository = RecipeRepository(RecipeDAO(recipeRoot))

        authRepository = AuthRepository (
            FirebaseAuth.getInstance(),contactRepository
        )
    }
}
