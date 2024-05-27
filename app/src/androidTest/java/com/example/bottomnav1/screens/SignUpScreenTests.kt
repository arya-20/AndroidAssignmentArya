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
    fun `move to the signup page`(){
        `move to login`()
        rule.onNode(signUpButton).performClick()
    }


    @Test
    fun `check default state of the sign up screen`() {
        `move to the signup page`()

        val pageTitle = hasText(rule.activity.getString(R.string.sign_up_screen_title))
        rule.onNode(pageTitle).assertExists()
        rule.onNode(bottomNavBar).assertDoesNotExist()
        rule.onNode(emailTextField).assertExists()
        rule.onNode(passwordTextField).assertExists()
        rule.onNode(submitButton).assertExists()
        rule.onNode(backButton).assertExists()
    }

    @Test
    fun `enter valid sign up details`(){
        `move to the signup page`()
        rule.onNode(emailTextField).performTextInput("aryagurjar20@gmail.com")
        rule.onNode(passwordTextField).performTextInput("password")
        rule.onNode(submitButton).performClick()
    }

    @Test
    fun `enter invalid sign up details`(){
        `move to the signup page`()
        rule.onNode(emailTextField).performTextInput("skjhfkjdshfskj")
        rule.onNode(passwordTextField).performTextInput("sdkjfhdskjhfk")
        rule.onNode(submitButton).performClick()
    }
}