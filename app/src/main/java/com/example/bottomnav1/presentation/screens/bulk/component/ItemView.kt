package com.example.bottomnav1.presentation.screens.bulk.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bottomnav1.data.recipe1.Recipe

@Composable
fun ItemView(
    index: Int,
    recipe: Recipe?,
    selected: Boolean,
    onClick: (Recipe?) -> Unit){
    recipe?.let { currentRecipe ->
        Text(
            text = currentRecipe.name ?: "",
            modifier = Modifier
                .clickable { onClick.invoke(currentRecipe) }
                .background(if (selected) MaterialTheme.colors.secondary else Color.Transparent)
                .fillMaxWidth()
                .padding(10.dp)
                .background(if (selected) Color.LightGray else Color.Transparent)
        )

    }
}