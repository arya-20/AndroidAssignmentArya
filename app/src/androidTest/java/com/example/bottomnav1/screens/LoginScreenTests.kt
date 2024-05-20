package com.example.bottomnav1.screens

import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
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
open class LoginScreenTests : ScreenTests(){

    @Before
    override fun setUp() {
        emailTextField = hasText(rule.activity.getString(R.string.email))
        passwordTextField = hasText(rule.activity.getString(R.string.password))
        submitButton = hasText(rule.activity.getString(R.string.submit_button)) and hasClickAction()
        forgotPasswordButton = hasText( rule.activity.getString(R.string.forgot_password)) and hasClickAction()
        signUpButton = hasText(rule.activity.getString(R.string.sign_up_button)) and hasClickAction()
    }

    @Test
    fun `check state of the login in page`() {
        val pageTitle = hasText(rule.activity.getString(R.string.login_screen_title))
        rule.onNode(pageTitle).assertExists()
        rule.onNode(submitButton).assertExists()
        rule.onNode(forgotPasswordButton).assertExists()
        rule.onNode(signUpButton).assertExists()
        rule.onNode(emailTextField).assertExists()
        rule.onNode(passwordTextField).assertExists()
        rule.onNode(bottomNavBar).assertDoesNotExist()

    }

    @Test
    fun `check if user can sign in and proceed to the home page`(){
        `sign in`()
        rule.onNode(bottomNavBar).assertExists()
    }

    @Test
    fun `sign in`() {
        rule.onNode(emailTextField).printToLog("UI_TEST");
        rule.onNode(emailTextField).performTextInput(VALID_EMAIL)
        rule.onNode(passwordTextField).performTextInput(VALID_PASSWORD)
        rule.onNode(submitButton).performClick()
        Thread.sleep(1000)
    }


    @Test
    fun `attempt sign in with invalid details`(){
        `sign in`(email = "invalid email",password="invalid password")
        `check state of the login in page`()
    }

    @Test
    fun `move to the sign up page`(){
        rule.onNode(signUpButton).performClick()
        val pageTitle = hasText(rule.activity.getString(R.string.sign_up_screen_title))
        rule.onNode(pageTitle).assertExists()
    }

    @Test
    fun `valid request for forgot password with email`(){
        rule.onNode(emailTextField).performTextInput(VALID_EMAIL)
        rule.onNode(forgotPasswordButton).performClick()

    }

    //@Test
    fun `invalid request for forgot password`(){

    }
}
//https://developer.android.com/training/testing/espresso/idling-resource