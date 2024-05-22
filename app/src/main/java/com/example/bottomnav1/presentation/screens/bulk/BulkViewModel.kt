package com.example.bottomnav1.presentation.screens.bulk

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bottomnav1.core.ContactApplication
import com.example.bottomnav1.data.DatabaseState
import com.example.bottomnav1.data.auth.AuthRepo
import com.example.bottomnav1.data.contact1.Contact
import com.example.bottomnav1.data.contact1.ContactRepository
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
    private val _userState = MutableStateFlow<DatabaseState<Contact?>>(DatabaseState())
    val userState: StateFlow<DatabaseState<Contact?>> = _userState

    var selectedRecipe: Recipe? by mutableStateOf(null)
    var recipeId by mutableStateOf("")

    init {
        val currentUser = authRepo.currentUser
        if (currentUser != null) {
            getCurrentUserDetails(currentUser.uid)
        }
    }

    //function to fetch bulk recipes
    private fun getCurrentUserDetails(userId: String) {
        viewModelScope.launch {
            val contact = contactRepo.getContactById(userId)
            _userState.value = DatabaseState(data = contact)
            contact?.let {fetchRecipesForUser (it)}
        }
    }

    private fun fetchRecipesForUser(contact: Contact) {
        viewModelScope.launch {
            val recipeResult = contact.recipe ?: emptyList()
            _recipeState.value = DatabaseState(data = recipeResult)
        }
    }

    //function to get recipe by ID
    suspend fun getRecipeById(recipeId: String): Recipe? {
        return recipeRepository.getRecipeById(recipeId)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                BulkViewModel(
                    authRepo = ContactApplication.container.authRepository,
                    recipeRepository = ContactApplication.container.recipeRepository,
                    contactRepo = ContactApplication.container.contactRepository
                )
            }
        }
    }
}