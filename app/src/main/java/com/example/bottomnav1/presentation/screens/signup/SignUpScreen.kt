package com.example.bottomnav1.presentation.screens.signup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bottomnav1.R
import com.example.bottomnav1.presentation.components.CustomButton
import com.example.bottomnav1.presentation.components.CustomTextField
import com.example.bottomnav1.presentation.components.SmallSpacer
import com.example.bottomnav1.presentation.screens.signup.components.SignUp
import com.example.bottomnav1.presentation.utils.Util.Companion.showMessage

@Composable
fun SignUpScreen(vm: SignUpViewModel = viewModel(factory = SignUpViewModel.Factory),
                 navigateBack: () -> Unit) {
    val context = LocalContext.current
    val keyboard = LocalSoftwareKeyboardController.current
    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.sign_up_screen_title),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
                SmallSpacer()
                SmallSpacer()
                SmallSpacer()
                SmallSpacer()


                CustomTextField(
                    stringResource(R.string.email),
                    text = vm.email,
                    onValueChange = { vm.email = it },
                    stringResource(R.string.email_error_message),
                    errorPresent = !vm.emailIsValid(),
                    isPasswordField = false

                    )
                CustomTextField(
                    stringResource(R.string.password),
                    text = vm.password,
                    onValueChange = { vm.password = it },
                    stringResource(R.string.password_error_message),
                    errorPresent = !vm.passwordIsValid(),
                    isPasswordField = true

                    )
                SmallSpacer()
                CustomButton(
                    stringResource(R.string.submit_button),
                    clickButton = {
                        keyboard?.hide()
                        vm.signUpWithEmailAndPassword()
                    }
                )
                SmallSpacer()
                Row {
                    CustomButton(
                        stringResource(R.string.back_button),
                        clickButton = {
                            navigateBack()
                        }
                    )
                }
            }
        }
    )

    SignUp(
        vm = vm,
        sendEmailVerification = {
            vm.sendEmailVerification()
        },
        showVerifyEmailMessage = {
            showMessage(context, "Confirm details via email")
        },
        showFailureToSignUpMessage = {
            showMessage(context, "Unable to create sign up due to permissions")
        }
    )
}
