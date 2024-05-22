package com.example.bottomnav1.data.recipe1

import android.util.Log
import com.example.bottomnav1.data.DatabaseResult
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface RecipeRepo {
    fun delete(recipe: Recipe): Task<Void>
    fun add(recipe: Recipe)
    fun edit(recipe: Recipe, recipeUUID: String)
    suspend fun getAll(recipeUUID: String): Flow<DatabaseResult<List<Recipe?>>>
    suspend fun getRecipeById(recipeId: String): Recipe?
}

class RecipeRepository(private val recipeDAO: RecipeDAO) : RecipeRepo {

    override fun delete(recipe: Recipe) = recipeDAO.delete(recipe)

    override fun add(recipe: Recipe) {
        val recipeId = UUID.randomUUID().toString()
        recipe.id = recipeId
        recipeDAO.insert(recipe, recipeId)
        Log.d("recipeDAO", "Recipe inserted: $recipe")
    }



    override fun edit(recipe: Recipe, recipeUUID: String) { recipeDAO.update(recipe, recipeUUID)}


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
