package com.example.bottomnav1.presentation.screens.bulk
// BulkPrepScreen.kt
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bottomnav1.data.recipe1.Recipe
import com.example.bottomnav1.presentation.components.BottomNavBar
import com.example.bottomnav1.presentation.screens.bulk.component.LazyColumnWithSelection
import com.example.bottomnav1.presentation.utils.Util.Companion.showMessage


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun BulkPrepScreen(
    navController: NavHostController,
    viewModel: BulkViewModel = viewModel(factory = BulkViewModel.Factory),
    onClickToViewRecipe: (String) -> Unit,
    onClickToEditRecipe: (String) -> Unit,
    onIndexChange: (Recipe?) -> Unit
) {
    //collect recipes from the view model
    val context = LocalContext.current


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bulk Prep Recipes",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            val recipeState by viewModel.recipeState.collectAsState()

            if (recipeState.data != null && recipeState.data!!.isNotEmpty()) {
                LazyColumnWithSelection(
                    recipes = recipeState.data!!
                ) { recipe ->
                    viewModel.selectedRecipe = recipe
                    recipe?.let { onIndexChange(it) }
                }
            }

            if (viewModel.recipeState.value.errorMessage.isNotBlank()) {
                showMessage(context, viewModel.recipeState.value.errorMessage)
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        viewModel.selectedRecipe?.let { recipe ->
                            recipe.id?.let { onClickToEditRecipe(it) }
                        }
                    },
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Text(text = "Edit")
                }
                Button(
                    onClick = {
                        viewModel.selectedRecipe?.let { recipe ->
                            recipe.id?.let { onClickToViewRecipe(it) }
                        }
                    }
                ) {
                    Text(text = "View")
                }
            }
        }
    }
}