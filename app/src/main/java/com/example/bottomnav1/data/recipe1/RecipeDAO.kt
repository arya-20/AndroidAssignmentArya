package com.example.bottomnav1.data.recipe1

import android.util.Log
import com.example.bottomnav1.data.DatabaseResult
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

        Log.d("RecipeDao", "Inserting recipe with ID: $recipeId")
        database.child(recipeId).setValue(newRecipe)
    }

    fun update(editRecipe: Recipe, userAuthUUID: String) {
        val recipeId = editRecipe.id.toString()
        editRecipe.id = String()
        database.child(userAuthUUID).child(recipeId).setValue(editRecipe)
    }

    fun delete(recipe: Recipe) = database.child(recipe.id.toString()).removeValue()


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
            val snapshot = database.child("recipes").child(recipeId).get().await()
            snapshot.getValue(Recipe::class.java)
        }catch (e: Exception) {
            null
        }
    }

}