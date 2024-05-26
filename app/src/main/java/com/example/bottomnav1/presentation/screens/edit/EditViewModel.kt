package com.example.bottomnav1.presentation.screens.edit

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bottomnav1.core.ContactApplication
import com.example.bottomnav1.data.auth.AuthRepo
import com.example.bottomnav1.data.recipe1.Recipe
import com.example.bottomnav1.data.recipe1.RecipeRepo
import kotlinx.coroutines.launch

class EditViewModel(
    private val authRepo: AuthRepo,
    private val repo: RecipeRepo
) : ViewModel() {

    private val _selectedRecipe = mutableStateOf<Recipe?>(null)
    val selectedRecipe: State<Recipe?> get() = _selectedRecipe

    var name by mutableStateOf("")
    var category by mutableStateOf(listOf<String>())
    var ingredients by mutableStateOf("")
    var instructions by mutableStateOf("")

    fun setSelectedContact(recipe: Recipe) {
        name = recipe.name.toString()
        category = recipe.category as List<String>
        ingredients = recipe.ingredients.toString()
        instructions = recipe.instructions.toString()
        _selectedRecipe.value = recipe
    }

    fun nameIsValid() = name.isNotBlank()
    fun categoryIsValid() = category.isNotEmpty()
    fun ingredientsIsValid() = ingredients.isNotBlank()
    fun instructionsIsValid() = instructions.isNotBlank()

    fun updateContact() {
        _selectedRecipe.value?.let { recipe ->
            if (nameIsValid() && categoryIsValid() && ingredientsIsValid() && instructionsIsValid()) {
                recipe.name = name
                recipe.category = null
                recipe.ingredients = ingredients
                recipe.instructions = instructions
                repo.edit(recipe, authRepo.currentUser!!.uid)
            }
        }
    }

    fun loadRecipeById(recipeId: String) {
        viewModelScope.launch {
            val recipe = repo.getRecipeById(recipeId)
            recipe?.let { setSelectedContact(it) }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                EditViewModel(
                    authRepo = ContactApplication.container.authRepository,
                    repo = ContactApplication.container.recipeRepository
                )
            }
        }
    }
}