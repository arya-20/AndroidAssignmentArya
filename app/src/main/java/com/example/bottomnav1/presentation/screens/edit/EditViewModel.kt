package com.example.bottomnav1.presentation.screens.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bottomnav1.core.ContactApplication
import com.example.bottomnav1.data.auth.AuthRepo
import com.example.bottomnav1.data.recipe1.Recipe
import com.example.bottomnav1.data.recipe1.RecipeRepo

class EditViewModel(private val authRepo: AuthRepo, private val repo: RecipeRepo) : ViewModel() {
    private var selectedRecipe: Recipe? = null

    var name by mutableStateOf(String())
    var category by mutableStateOf(listOf<String>())
    var ingredients by mutableStateOf(String())
    var instructions by mutableStateOf(String())

    fun setSelectedContact(recipe: Recipe) {
        name = recipe.name.toString()
//        category = recipe.category.toString()
        ingredients = recipe.ingredients.toString()
        instructions = recipe.instructions.toString()
        selectedRecipe = recipe
    }

    fun nameIsValid(): Boolean {
        return name.isNotBlank()
    }

    fun categoryIsValid(): Boolean {
        return category.isNotEmpty()
    }

    fun ingredientsIsValid(): Boolean {
        return ingredients.isNotBlank()
    }
    fun instructionsIsValid(): Boolean {
        return instructions.isNotBlank()
    }

    fun updateContact() {
        if (selectedRecipe != null
            && nameIsValid()
            && categoryIsValid()
            && ingredientsIsValid()
            && instructionsIsValid()
        ) {
            selectedRecipe!!.name = name
//            selectedRecipe!!.category = category
            selectedRecipe!!.ingredients = ingredients
            selectedRecipe!!.instructions = instructions

            repo.edit(selectedRecipe!!, authRepo.currentUser!!.uid)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory() {
            initializer {
                EditViewModel(
                    authRepo = ContactApplication.container.authRepository,
                    repo = ContactApplication.container.recipeRepository
                )
            }
        }
    }
}