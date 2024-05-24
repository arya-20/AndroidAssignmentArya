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
    private  val recipeRepo : RecipeRepository
) : ViewModel() {
    private val recipeId: String = ""
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
        val trimmedRecipeId = recipeId?.trim()
        if (trimmedRecipeId.isNullOrEmpty()) return

        val recipe = recipeRepo.getRecipeById(trimmedRecipeId) ?: return

        name = recipe.name ?: ""
        category = recipe.category?.name ?: ""
        ingredients = recipe.ingredients ?: ""
        instructions = recipe.instructions ?: ""
    }

    fun deleteRecipe() {
        viewModelScope.launch {
            recipeRepo.delete(recipe!!)
            recipe = null
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                RecipeDetailViewModel(
                    recipeRepo = ContactApplication.container.recipeRepository
                )
            }
        }
    }
}
