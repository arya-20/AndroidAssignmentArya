package com.example.bottomnav1.presentation.screens.recipe
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bottomnav1.core.ContactApplication
import com.example.bottomnav1.data.recipe1.Recipe
import com.example.bottomnav1.data.recipe1.RecipeRepository
import kotlinx.coroutines.launch

class RecipeDetailViewModel(

    private  val recipeRepo : RecipeRepository,
    private val recipeId: String,

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

        val fetchedRecipe = recipeRepo.getRecipeById(trimmedRecipeId)
        if (fetchedRecipe != null) {
            recipe = fetchedRecipe
            name = fetchedRecipe.name ?: ""
            category = fetchedRecipe.category?.name ?: ""
            ingredients = fetchedRecipe.ingredients ?: ""
            instructions = fetchedRecipe.instructions ?: ""
        }
    }





    fun deleteRecipe() {
        viewModelScope.launch {
            val recipeToDelete = recipe
            if (recipeToDelete != null) {
                recipeRepo.delete(recipeToDelete)
                    .addOnSuccessListener {
                        recipe = null
                    }
            }
        }
    }

    companion object {
        fun Factory(recipeId: String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                RecipeDetailViewModel(
                    recipeId = recipeId,
                    recipeRepo = ContactApplication.container.recipeRepository
                )
            }
        }
    }
}
