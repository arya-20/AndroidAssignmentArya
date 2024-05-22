package com.example.bottomnav1.presentation.screens.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bottomnav1.R
import com.example.bottomnav1.data.recipe1.Recipe
import com.example.bottomnav1.presentation.components.BottomNavBar
import com.example.bottomnav1.presentation.components.CustomButton
import com.example.bottomnav1.presentation.components.CustomTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditScreen(vm: EditViewModel = viewModel(factory = EditViewModel.Factory),
               modifier: Modifier = Modifier,
               navController: NavHostController,
               selectedRecipe: Recipe,
               onClickToHome: () -> Unit){

    LaunchedEffect(key1 = Unit) {//Called on launch
        vm.setSelectedContact(selectedRecipe)
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                    stringResource(R.string.name),
                    text = vm.name,
                    onValueChange = { vm.name = it },
                    errorMessage = stringResource(R.string.name_error),
                    errorPresent = vm.nameIsValid()
                )

//                CustomTextField(
//                    stringResource(R.string.category),
//                    text = vm.password,
//                    onValueChange = { vm.password = it },
//                    errorMessage = stringResource(R.string.surname_error_message),
//                    errorPresent = vm.passwordIsValid()
//                )

                CustomTextField(
                    stringResource(R.string.ingredients),
                    text = vm.ingredients,
                    onValueChange = { vm.ingredients = it },
                    errorMessage = stringResource(R.string.ingredients),
                    errorPresent = vm.ingredientsIsValid()
                )

                CustomTextField(
                    stringResource(R.string.instructions),
                    text = vm.instructions,
                    onValueChange = { vm.instructions = it },
                    errorMessage = stringResource(R.string.instructions),
                    errorPresent = vm.instructionsIsValid()
                )


                CustomButton(
                    stringResource(R.string.edit),
                    clickButton = {
                        vm.updateContact()
                        onClickToHome()
                    })
            }
        }
    }
}
