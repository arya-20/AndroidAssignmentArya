package com.example.bottomnav1.data.recipe1

import android.util.Log
import com.example.bottomnav1.data.DatabaseResult
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class RecipeDAO(private var database: DatabaseReference) {
    private var event: ValueEventListener?=null

    fun updateContactListener(recipesToListenTo: DatabaseReference){
        this.database = recipesToListenTo
    }

    fun insert(newRecipe: Recipe, recipeId: String) {

        database.child(recipeId).setValue(newRecipe)
    }

    fun update(editRecipe: Recipe, userAuthUUID: String) {
        val recipeId = editRecipe.id.toString()
        editRecipe.id = String()
        database.child(userAuthUUID).child(recipeId).setValue(editRecipe)
    }

    fun delete(recipe: Recipe): Task<Void> {
        val recipeId = recipe.id
        return if (recipeId != null) {
            database.child(recipeId).removeValue()
                .addOnSuccessListener {
                    Log.d("RecipeDAO", "Successfully deleted recipe with ID: $recipeId")
                }
                .addOnFailureListener { exception ->
                    Log.e("RecipeDAO", "Failed to delete recipe with ID: $recipeId", exception)
                }
        } else {
            Log.e("RecipeDAO", "Cannot delete recipe. ID is null.")
            Tasks.forException(IllegalArgumentException("Recipe ID is null"))
        }
    }


    suspend fun getRecipes(category: String): Flow<DatabaseResult<List<Recipe?>>> = callbackFlow {
        trySend(DatabaseResult.Loading)
        database.child(category).keepSynced(true)

        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val recipes = ArrayList<Recipe>()
                for (childSnapshot in snapshot.children) {
                    val recipe = childSnapshot.getValue(Recipe::class.java)
                    recipe!!.id = childSnapshot.key.toString()
                    recipes.add(recipe)
                }
                trySend(DatabaseResult.Success(recipes))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(DatabaseResult.Error(Throwable(error.message)))
            }
        }
        database.child(category).addValueEventListener(event)
        awaitClose { close() }
    }
    suspend fun getRecipeById(recipeId: String): Recipe? {
        return try {
            Log.d("RecipeDao", "Fetching recipe with ID: $recipeId from database")

            val snapshot = database.child(recipeId).get().await()

            if (snapshot.exists()) {
                Log.d("RecipeDao", "Snapshot exists for ID: $recipeId")
                snapshot.getValue(Recipe::class.java)
            } else {
                Log.d("RecipeDao", "No recipe found for ID: $recipeId")
                null
            }
        } catch (e: Exception) {
            Log.e("RecipeDao", "Error fetching recipe for ID: $recipeId", e)
            null
        }
    }
}