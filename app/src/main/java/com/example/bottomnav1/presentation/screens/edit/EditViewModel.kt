package com.example.bottomnav1.presentation.screens.edit

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
import com.example.bottomnav1.data.recipe1.Category
import com.example.bottomnav1.data.recipe1.Recipe
import com.example.bottomnav1.data.recipe1.RecipeRepo
import kotlinx.coroutines.launch

class EditViewModel(
    private val authRepo: AuthRepo,
    private val repo: RecipeRepo,
    private val recipeId: String
) : ViewModel() {

//    private val _selectedRecipe = mutableStateOf<Recipe?>(null)
//    val selectedRecipe: State<Recipe?> get() = _selectedRecipe

    var name by mutableStateOf("")
    var category by mutableStateOf<Category?>(null)
    var ingredients by mutableStateOf("")
    var instructions by mutableStateOf("")

    init {
        viewModelScope.launch { val recipe = repo.getRecipeById(recipeId)
        if (recipe != null) {
            name = recipe.name ?: ""
            ingredients = recipe.ingredients?: ""
            instructions = recipe.instructions?: ""

        }
        }
    }


    fun nameIsValid() = name.isNotBlank()
    fun ingredientsIsValid() = ingredients.isNotBlank()
    fun instructionsIsValid() = instructions.isNotBlank()

    fun updateContact() {
            if (nameIsValid() && ingredientsIsValid() && instructionsIsValid()) {
                val updatedRecipe = Recipe(
                id = recipeId,
                    name = name,
                category = category,
                ingredients = ingredients,
                instructions = instructions
                )
                viewModelScope.launch{
                repo.edit(updatedRecipe)
            }
        }
    }

    private fun clear (){
        name = ""
        category = null
        ingredients = ""
        instructions = ""
    }

    companion object {
        fun Factory (recipeId: String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                EditViewModel(
                    authRepo = ContactApplication.container.authRepository,
                    repo = ContactApplication.container.recipeRepository,
                    recipeId = recipeId.trim()
                )
            }
        }
    }
}