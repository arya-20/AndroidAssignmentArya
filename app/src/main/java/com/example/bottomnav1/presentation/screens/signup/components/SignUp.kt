package com.example.bottomnav1.presentation.screens.signup.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.bottomnav1.data.Response
import com.example.bottomnav1.presentation.components.ProgressBar
import com.example.bottomnav1.presentation.screens.signup.SignUpViewModel

@Composable
fun SignUp(vm: SignUpViewModel,
           sendEmailVerification: () -> Unit,
           showVerifyEmailMessage: () -> Unit,
           showFailureToSignUpMessage: () -> Unit
)  {
    when(val signUpResponse = vm.signUpResponse) {
        is Response.Loading, Response.Startup -> ProgressBar()
        is Response.Success -> {
            val isUserSignedUp = signUpResponse.data
            LaunchedEffect(isUserSignedUp) {
                if (isUserSignedUp) {
                    sendEmailVerification()
                    showVerifyEmailMessage()
                }
            }
        }
        is Response.Failure -> signUpResponse.apply {
            LaunchedEffect(e) {
                print(e)
                showFailureToSignUpMessage()
            }
        }
    }
}
