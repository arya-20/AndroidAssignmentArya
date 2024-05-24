package com.example.bottomnav1.screens

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.bottomnav1.R
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder( MethodSorters.DEFAULT)
class SignUpScreenTests : ScreenTests() {


    @Before
    override fun setUp() {
        super.setUp()
    }


    @Test
    fun `check default state of the sign up screen`() {
        rule.onNode(signUpButton).performClick()
        val pageTitle =
            hasText(rule.activity.getString(R.string.sign_up_screen_title))
        rule.onNode(startScreenTitle).assertExists()
        rule.onNode(bottomNavBar).assertDoesNotExist()
        rule.onNode(emailTextField).assertExists()
        rule.onNode(passwordTextField).assertExists()
        rule.onNode(submitButton).assertExists()
        rule.onNode(backButton).assertExists()
    }

    @Test
    fun `enter valid sign up details`(){
        rule.onNode(signUpButton).performClick()
        rule.onNode(emailTextField).performTextInput("test@gmail.com") //must be a valid email or firebase will put up an error via toast
        rule.onNode(passwordTextField).performTextInput("password")
        rule.onNode(submitButton).performClick()
    }

    @Test
    fun `enter invalid sign up details`(){
        rule.onNode(signUpButton).performClick()
        rule.onNode(emailTextField).performTextInput("inavlidexample@invalid")
        rule.onNode(passwordTextField).performTextInput("password")
        rule.onNode(submitButton).performClick()
    }
}