package com.example.bottomnav1.presentation.screens.recipe
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bottomnav1.core.ContactApplication
import com.example.bottomnav1.data.contact1.ContactRepository
import com.example.bottomnav1.data.recipe1.Recipe
import com.example.bottomnav1.data.recipe1.RecipeRepository
import kotlinx.coroutines.launch

class RecipeDetailViewModel(

    private  val recipeRepo : RecipeRepository,
    private val recipeId: String,
    private val contactRepo: ContactRepository,

    ) : ViewModel() {
    var name: String = ""
    var category: String = ""
    var ingredients: String = ""
    var instructions: String = ""
    var recipe: Recipe?= null
    init {
        viewModelScope.launch {
            fetchRecipeDetails()
        }
    }

    private suspend fun fetchRecipeDetails() {
        val trimmedRecipeId = recipeId.trim()
        if (trimmedRecipeId.isEmpty()) {
            return
        }

        val fetchedRecipe = recipeRepo.getRecipeById(recipeId)
        if (fetchedRecipe != null) {
            recipe = fetchedRecipe
            name = fetchedRecipe.name ?: ""
            category = fetchedRecipe.category?.name ?: ""
            ingredients = fetchedRecipe.ingredients ?: ""
            instructions = fetchedRecipe.instructions ?: ""
        }
    }


    fun deleteRecipeFromCurrentUser(recipeId: String) {
        viewModelScope.launch {
            val currentUser = contactRepo.getCurrentContact()
            if (currentUser != null) {
                val currentUserCopy = currentUser.copy()
                Log.d("recipe11","$recipeId")
                val currentRecipe = recipeRepo.getRecipeById(recipeId)
                Log.d("recipe class", "$currentRecipe")
                val updatedRecipeIds =
                    (currentUserCopy.recipe ?: emptyList()).toMutableList().apply {
                        remove(currentRecipe)
                    }
                currentUserCopy.recipe = updatedRecipeIds
                contactRepo.updateContact(currentUserCopy)
            }
        }
    }




    companion object {
        fun Factory(recipeId: String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                RecipeDetailViewModel(
                    recipeId = recipeId.trim(),
                    recipeRepo = ContactApplication.container.recipeRepository,
                    contactRepo = ContactApplication.container.contactRepository
                )
            }
        }
    }
}
