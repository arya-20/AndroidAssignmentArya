package com.example.bottomnav1.presentation.screens.recipe
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bottomnav1.core.ContactApplication
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

    init {
        viewModelScope.launch {
            fetchRecipeDetails()
        }
    }

    private suspend fun fetchRecipeDetails() {
        val recipe = recipeRepo.getRecipeById(recipeId) ?: return

                name = recipe.name ?: ""
                category = (recipe.category ?: "").toString()
                ingredients = recipe.ingredients ?: ""
                instructions = recipe.instructions ?: ""
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
