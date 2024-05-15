package com.example.bottomnav1.presentation.screens.add

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
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
fun AddScreen(vm: AddViewModel = viewModel(factory = AddViewModel.Factory),
              modifier: Modifier = Modifier,
              navController: NavHostController,
              onClickToHome: () -> Unit
              ) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    )
    {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            androidx.compose.material.Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.add),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            Column {

                CustomTextField(
                    stringResource(R.string.email),
                    text = vm.email,
                    onValueChange = { vm.email = it },
                    errorMessage = stringResource(R.string.email_error_message),
                    errorPresent = vm.isEmailValid(email = String())
                )

                CustomTextField(
                    stringResource(R.string.password_hint),
                    text = vm.password,
                    onValueChange = { vm.password = it },
                    errorMessage = stringResource(R.string.password_error_message),
                    errorPresent = vm.isPasswordValid(password = String())
                )


                CustomButton(
                    stringResource(R.string.add),
                    clickButton = {
                        vm.addContact()
                        keyboardController?.hide()
                        onClickToHome()
                    })
            }
        }
    }
}
