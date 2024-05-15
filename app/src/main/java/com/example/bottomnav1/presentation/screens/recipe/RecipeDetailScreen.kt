package com.example.bottomnav1.presentation.screens.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener


@Composable
fun RecipeDetailsScreen(recipeName: String, navController: NavController, database: DatabaseReference) {
    var recipeDetails by remember { mutableStateOf("Loading...") }

    //fetch recipe details from firebase based on the recipe name
    LaunchedEffect(recipeName) {
        val recipeRef = database.child("Bulk").child(recipeName)
        val recipeListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val recipe = dataSnapshot.getValue(String::class.java)
                recipe?.let {
                    recipeDetails = it
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
                recipeDetails = "Error getting recipe details"
            }
        }
        recipeRef.addListenerForSingleValueEvent(recipeListener)

        }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //display the recipe name as the title of the screen
        Text(
            text = recipeName,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = recipeDetails,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}