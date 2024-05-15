package com.example.bottomnav1.presentation.screens.recipe
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bottomnav1.core.ContactApplication
import com.example.bottomnav1.data.recipe1.Category
import kotlinx.coroutines.launch

class RecipeDetailViewModel : ViewModel() {
    var name: String = ""
    var category: Category? = null
    var ingredients: List<String>? = null
    var instructions: String? = null

    fun fetchRecipeDetails(recipeId: String) {
        viewModelScope.launch {
            val recipe = ContactApplication.container.recipeRepository.getRecipeById(recipeId)
            recipe?.let {
                name = it.name ?: ""
                category = it.category
                ingredients = it.ingredients
                instructions = it.instructions
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                RecipeDetailViewModel()
            }
        }
    }
}
