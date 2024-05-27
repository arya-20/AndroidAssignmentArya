package com.example.bottomnav1.screens

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bottomnav1.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder( MethodSorters.DEFAULT)
@RunWith(AndroidJUnit4::class)
open class LoginScreenTests : ScreenTests() {

    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun `move to the login page`(){
        rule.onNode(startButton).performClick()
    }
    @Test
    fun `check state of the login in page`() {
        `move to the login page`()

        rule.onNode(appLogo).assertExists()
        rule.onNode(loginTitle).assertExists()
        rule.onNode(submitButton).assertExists()
        rule.onNode(forgotPasswordButton).assertExists()
        rule.onNode(signUpButton).assertExists()
        rule.onNode(emailTextField).assertExists()
        rule.onNode(passwordTextField).assertExists()
        rule.onNode(bottomNavBar).assertDoesNotExist()

    }
    @Test
    fun `test log in`() {
        `move to the login page`()
        rule.onNode(emailTextField).performTextInput(VALID_EMAIL)
        rule.onNode(passwordTextField).performTextInput(VALID_PASSWORD)
        rule.onNode(submitButton).performClick()

        Thread.sleep(1000)
    }
    @Test
    fun `attempt sign in with invalid details`(){
        `move to the login page`()

        rule.onNode(emailTextField).performTextInput(INVALID_EMAIL)
        rule.onNode(passwordTextField).performTextInput(INVALID_PASSWORD)
        rule.onNode(submitButton).performClick()

        Thread.sleep(1000)
    }
    @Test
    fun `move to the sign up page`(){
        `move to the login page`()

        rule.onNode(signUpButton).performClick()
        val pageTitle = hasText(rule.activity.getString(R.string.sign_up_screen_title))
        rule.onNode(pageTitle).assertExists()
    }
    @Test
    fun `valid request for forgot password with email`(){
        `move to the login page`()
        rule.onNode(emailTextField).performTextInput(VALID_EMAIL)
        rule.onNode(forgotPasswordButton).performClick()

    }
    @Test
    fun `invalid request for forgot password`(){
        `move to the login page`()
        rule.onNode(emailTextField).performTextInput(INVALID_EMAIL)
        rule.onNode(forgotPasswordButton).performClick()

    }
    @Test
    fun `check if user can sign in and proceed to the home page`(){
        `log in`()
        val pageTitle = hasText(rule.activity.getString(R.string.home))
        rule.onNode(pageTitle).assertExists()
        rule.onNode(bottomNavBar).assertExists()
    }

}








//https://developer.android.com/training/testing/espresso/idling-resource
