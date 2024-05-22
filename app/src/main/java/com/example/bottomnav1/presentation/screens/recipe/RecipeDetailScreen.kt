package com.example.bottomnav1.presentation.screens.recipe

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bottomnav1.R


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeDetailsScreen(recipeName: String,
                        vm:  RecipeDetailViewModel = viewModel(factory = RecipeDetailViewModel.Factory),
                        navController: NavController) {

    val backStackEntry = navController.currentBackStackEntry
    val recipeId = backStackEntry?.arguments?.getString("recipeId") ?: return

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Recipe Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painterResource(id = R.drawable.back),
                            contentDescription = "Back")
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
    )

    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "Name: ${vm.name }",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Category: ${vm.category }",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Ingredients: ${vm.ingredients }",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Instructions: ${vm.instructions }",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
                }
            }
        }
