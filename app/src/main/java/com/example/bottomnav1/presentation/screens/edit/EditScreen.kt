package com.example.bottomnav1.presentation.screens.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bottomnav1.R
import com.example.bottomnav1.presentation.components.BottomNavBar
import com.example.bottomnav1.presentation.components.CustomButton
import com.example.bottomnav1.presentation.components.CustomTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditScreen(
    recipeId: String,
    vm: EditViewModel = viewModel(factory = EditViewModel.Factory(recipeId)),
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onClickToHome: () -> Unit
) {


    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.edit),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            Column {
                CustomTextField(
                    modifier = Modifier.semantics {
                        contentDescription = "Name"
                    },
                    stringResource(R.string.name),
                    text = vm.name,
                    onValueChange = { vm.name = it },
                    errorMessage = stringResource(R.string.name_error),
                    errorPresent = !vm.nameIsValid()
                )

                CustomTextField(
                    modifier = Modifier.semantics {
                        contentDescription = "Ingredients"
                    },
                    stringResource(R.string.ingredients),
                    text = vm.ingredients,
                    onValueChange = { vm.ingredients = it },
                    errorMessage = stringResource(R.string.ingredients_error),
                    errorPresent = !vm.ingredientsIsValid()
                )

                CustomTextField(
                    modifier = Modifier.semantics {
                        contentDescription = "Instructions"
                    },
                    stringResource(R.string.instructions),
                    text = vm.instructions,
                    onValueChange = { vm.instructions = it },
                    errorMessage = stringResource(R.string.instructions_error),
                    errorPresent = !vm.instructionsIsValid()
                )

                CustomButton(
                    stringResource(R.string.edit),
                    clickButton = {
                        vm.updateContact()
                        onClickToHome()
                    }
                )
            }
        }
    }
}
