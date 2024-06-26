package com.example.bottomnav1.presentation.screens.recipe

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
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
import androidx.navigation.NavHostController
import com.example.bottomnav1.R
import com.example.bottomnav1.data.recipe1.Recipe
import com.example.bottomnav1.presentation.navigation.NavScreen
import com.example.bottomnav1.presentation.theme.DarkBlue


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeDetailsScreen(
    recipe: Recipe,
    recipeName: String,
    recipeId: String,
    onClickToEditRecipe: (String) -> Unit,
    vm:  RecipeDetailViewModel = viewModel(factory = RecipeDetailViewModel.Factory(recipeId.trim())),
    navController: NavHostController

    ) {
    val backStackEntry = navController.currentBackStackEntry
    val recipeId = backStackEntry?.arguments?.getString("recipeId")?.trim() ?: return


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Recipe Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painterResource(id = R.drawable.back),
                            contentDescription = "Back",
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .size(50.dp))

                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            Column {
                FloatingActionButton(
                    onClick = { navController.navigate("${NavScreen.Edit.route}/${recipeId}") },
                    backgroundColor = DarkBlue,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit",
                        tint = Color.White
                    )
                }
                FloatingActionButton(
                    onClick = {
                        vm.deleteRecipeFromCurrentUser(recipeId)
                        navController.popBackStack()
                    },
                    backgroundColor = Color.Red,
                    modifier = Modifier.padding(8.dp)

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = "Delete",
                        tint = Color.White
                    )
                }
            }
        }

    )

    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "Name: ${vm.name}",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Category: ${vm.category}",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Ingredients: ${vm.ingredients}",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Instructions: ${vm.instructions}",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
