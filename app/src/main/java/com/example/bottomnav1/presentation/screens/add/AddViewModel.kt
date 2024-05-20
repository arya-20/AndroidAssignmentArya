package com.example.bottomnav1.presentation.screens.add

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bottomnav1.core.ContactApplication
import com.example.bottomnav1.data.auth.AuthRepo
import com.example.bottomnav1.data.recipe1.Category
import com.example.bottomnav1.data.recipe1.Recipe
import com.example.bottomnav1.data.recipe1.RecipeRepository

class AddViewModel (private val authRepo: AuthRepo, private val repo: RecipeRepository) : ViewModel() {
    var name by mutableStateOf("")
    var category by mutableStateOf <Category?> (null)
    var ingredients by mutableStateOf("")
    var instructions by mutableStateOf("")

    fun isNameValid(): Boolean {
        return name.isNotBlank()
    }
    fun isCategoryValid(): Boolean {
        return category in Category.values()
    }
    fun isIngredientsValid(): Boolean {
        return ingredients.isNotBlank()
    }fun isInstructionsValid(): Boolean {
        return instructions.isNotBlank()
    }
    fun addContact() {
        if (isNameValid() && isInstructionsValid() && isIngredientsValid() && isCategoryValid() ) {
            val selectedCategory = category!!
            val newRecipe = Recipe(
                name = name,
                category = category,
                ingredients = ingredients,
                instructions = instructions
            )
            repo.add(newRecipe)

            Log.d("new recipe :", "$newRecipe")
            clearFields()
        }
    }

    private fun clearFields() {
        name = ""
        category = null
        ingredients = ""
        instructions = ""
          }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AddViewModel(
                    authRepo = ContactApplication.container.authRepository,
                    repo = ContactApplication.container.recipeRepository
                )
            }
        }

    }
}