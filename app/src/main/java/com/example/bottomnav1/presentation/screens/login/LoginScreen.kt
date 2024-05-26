package com.example.bottomnav1.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bottomnav1.R
import com.example.bottomnav1.presentation.components.CustomButton
import com.example.bottomnav1.presentation.components.CustomTextField
import com.example.bottomnav1.presentation.components.SmallSpacer
import com.example.bottomnav1.presentation.screens.login.components.LogIn
import com.example.bottomnav1.presentation.utils.Util.Companion.showMessage

@Composable
fun LoginScreen(vm: LoginViewModel = viewModel(factory = LoginViewModel.Factory),
                navigateToSignUpScreen: () -> Unit,
                navigateToHomeScreen: () -> Unit) {
        val context = LocalContext.current
        val message: String by vm.message.observeAsState(String())
        if (message.length > 0) { //Only changes when vm message is updated
            showMessage(context, vm.message.value)
        }

        Scaffold( modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        content = { padding ->
            val keyboard = LocalSoftwareKeyboardController.current

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .size(150.dp)
                )
                Text(
                    text = stringResource(R.string.login_screen_title),
                    textAlign = TextAlign.Center,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold
                )
                CustomTextField(
                    modifier = Modifier.semantics {
                        contentDescription = context.getString(R.string.email)
                    },
                    hintText = stringResource(R.string.email),
                    text = vm.email,
                    onValueChange = { vm.email = it },
                    stringResource(R.string.email_error_message),
                    !vm.emailIsValid(),
                    isPasswordField = false
                )
                CustomTextField(
                    modifier = Modifier.semantics {
                        contentDescription = context.getString(R.string.password)
                    },
                    hintText = stringResource(R.string.password),
                    text = vm.password,
                    onValueChange = { vm.password = it },
                    stringResource(R.string.password_error_message),
                    !vm.passwordIsValid() ,
                    isPasswordField = true

                    )
                SmallSpacer()
                CustomButton(
                    stringResource(R.string.submit_button),
                    clickButton = {
                        keyboard?.hide()
                        vm.signInWithEmailAndPassword()
                    }
                )
                SmallSpacer()
                CustomButton(
                    stringResource(R.string.forgot_password),
                    clickButton = {
                        if (vm.emailIsValid()) {
                            vm.forgotPassword()
                        } else {
                            showMessage(context, "Valid email required to retrieve password")
                        }
                    },
                    buttonWidth = 240,
                    buttonHeight = 40
                )
                SmallSpacer()
                CustomButton(
                    text = stringResource(R.string.sign_up_button),
                    clickButton = { navigateToSignUpScreen() },
                    buttonWidth = 240,
                    buttonHeight = 40,
                )
            }
        }
        )

        LogIn(
            vm = vm,
            showErrorMessage = { errorMessage ->
                showMessage(context, errorMessage)
            },
            navigateToHomeScreen = navigateToHomeScreen
        )
    }

