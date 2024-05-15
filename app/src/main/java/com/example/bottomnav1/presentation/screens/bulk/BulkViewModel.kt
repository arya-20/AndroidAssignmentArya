package com.example.bottomnav1.presentation.screens.bulk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bottomnav1.core.ContactApplication
import com.example.bottomnav1.data.DatabaseState
import com.example.bottomnav1.data.auth.AuthRepo
import com.example.bottomnav1.data.contact1.ContactRepository
import com.example.bottomnav1.data.recipe1.Category
import com.example.bottomnav1.data.recipe1.Recipe
import com.example.bottomnav1.data.recipe1.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class BulkViewModel(
    private val authRepo: AuthRepo,
    private val recipeRepository: RecipeRepository,
    private val contactRepo: ContactRepository
) : ViewModel() {

    private val _recipeState = MutableStateFlow(DatabaseState<List<Recipe>>())
    val recipeState: StateFlow<DatabaseState<List<Recipe>>> = _recipeState.asStateFlow()

    var selectedRecipe: Recipe? by mutableStateOf(null)
    var recipeId by mutableStateOf("")
    init {
        val currentUser = authRepo.currentUser
        if (currentUser != null) {
            getBulkRecipesForCurrentUser(currentUser.uid)
        }
    }

    //function to fetch bulk recipes
    private fun getBulkRecipesForCurrentUser(userId: String) {
        viewModelScope.launch {
            try {
                _recipeState.value = _recipeState.value.copy(isLoading = true)

                //fetch current user's contacts
                val currentUserContact = contactRepo.getContactForUser(userId)

                val bulkRecipes = currentUserContact?.recipe?.filter { recipe ->
                    recipe.category == Category.BULK
                } ?: emptyList()

                _recipeState.value = DatabaseState(data = bulkRecipes)

            } catch (e: Exception) {
                _recipeState.value = _recipeState.value.copy(errorMessage = e.message ?: "Unknown error")
            } finally {
                _recipeState.value = _recipeState.value.copy(isLoading = false)
            }
        }
    }

    //function to get recipe by ID
    suspend fun getRecipeById(recipeId: String): Recipe? {
        return recipeRepository.getRecipeById(recipeId)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            BulkViewModel(
                ContactApplication.container.authRepository,
                ContactApplication.container.recipeRepository,
                ContactApplication.container.contactRepository
            )
        }
    }
}
