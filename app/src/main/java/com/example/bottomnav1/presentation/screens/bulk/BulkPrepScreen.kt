package com.example.bottomnav1.presentation.screens.bulk
// BulkPrepScreen.kt
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bottomnav1.R
import com.example.bottomnav1.core.ContactApplication
import com.example.bottomnav1.data.recipe1.Recipe
import com.example.bottomnav1.presentation.components.BottomNavBar
import com.example.bottomnav1.presentation.theme.DarkBlue


@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun BulkPrepScreen(
    navController: NavHostController,
    viewModel: BulkViewModel = viewModel(factory = BulkViewModel.Factory),
    onClickToRecipeDetailScreen: (String) -> Unit,
    onClickToAddRecipe: () -> Unit,

    ) {
    //collect recipes from the view model
    val context = LocalContext.current.applicationContext as ContactApplication
    val recipeState by viewModel.recipeState.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        topBar = {
            Column {
                Text(
                    text = "Bulk Preparation Recipes",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(top = 26.dp, start = 16.dp, bottom = 8.dp)
                )
                Text(
                    text = "Preparing meals in bulk can save you time and money. You can add your bulk prep recipes below using the add button.",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick =  onClickToAddRecipe ,
                backgroundColor = DarkBlue
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "Add",
                    tint = White,)
            }
        }
    )

    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            val recipeState = recipeState.data ?: emptyList()
            if (recipeState.isNotEmpty()) {
                RecipeSection(
                    "",
                    recipeState
                ) { recipeId ->
                    onClickToRecipeDetailScreen(recipeId)
                }
            }
        }
    }
}

@Composable
fun RecipeSection(title: String, recipe: List<Recipe>, onClick: (String) -> Unit) {
    val isSelected = remember{ mutableStateOf(false) }

    val backgroundColor = if (isSelected.value) White else Gray
    Surface(
        color = White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(27.dp))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Black,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = 16.dp)
            )
            Box(
                modifier = Modifier
                    .background(backgroundColor)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable(
                        onClick = { isSelected.value = !isSelected.value }
                    )
            ) {
                LazyColumn(modifier = Modifier.fillMaxWidth())
                {
                    items(recipe) { recipe ->
                        RecipeItem(
                            recipe,
                            onClick = { onClick(recipe.id?:"") })
                    }
                }
            }
        }
    }
}
 @Composable
 fun RecipeItem (recipe: Recipe, onClick: () -> Unit) {
     Surface(
         color = White,
         modifier = Modifier
             .fillMaxWidth()
             .padding(vertical = 8.dp, horizontal = 18.dp)
             .clip(RoundedCornerShape(24.dp))
             .clickable(onClick = onClick)
     ) {
         Column(
             modifier = Modifier.padding(16.dp)) {
             Text(
                 text = recipe.name?: "",
                 fontSize = 18.sp,
                 fontWeight = FontWeight.Bold,
                 color = Black,
                 textAlign = TextAlign.Start,
                 modifier = Modifier.padding(bottom = 8.dp)
             )
         }
     }
 }