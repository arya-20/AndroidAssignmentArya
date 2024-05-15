package com.example.bottomnav1.data.recipe1

import com.example.bottomnav1.data.DatabaseResult
import kotlinx.coroutines.flow.Flow

interface RecipeRepo {

    suspend fun getAll(recipeUUID: String): Flow<DatabaseResult<List<Recipe?>>>

    suspend fun getRecipeById(recipeId: String): Recipe?
}

class RecipeRepository(private val recipeDAO: RecipeDAO) : RecipeRepo {

    override suspend fun getAll(recipeUUID: String): Flow<DatabaseResult<List<Recipe?>>> {
        return recipeDAO.getRecipes(recipeUUID)
    }

    override suspend fun getRecipeById(recipeId: String): Recipe? {
        return try {
            recipeDAO.getRecipeById(recipeId)
        } catch (e: Exception) {
            null
        }
    }
}
