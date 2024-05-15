package com.example.bottomnav1.presentation.screens.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.bottomnav1.data.recipe1.Recipe

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LazyColumnWithSelection(recipes: List<Recipe?>,
                            onIndexChange: (Recipe?) -> Unit){
    var selectedIndexToHighlight by remember { mutableIntStateOf(-1) }

    LazyColumn {
        itemsIndexed(recipes) { index, recipe ->
            ItemView(
                index = index,
                recipe = recipe,
                selected = selectedIndexToHighlight == index,
                onClick = { selectedRecipe ->
                    selectedIndexToHighlight = index //local state for highlighting selected item
                    onIndexChange(selectedRecipe)
                }
            )
        }
    }
}