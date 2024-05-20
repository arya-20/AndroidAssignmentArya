package com.example.bottomnav1.screens

import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.bottomnav1.R
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder( MethodSorters.DEFAULT)
class SignUpPageTests : ScreenTests() {

    @Before
    override fun setUp() {
        val BUTTON_POSTFIX = " button"
        submitButton = hasContentDescription(rule.activity.getString(R.string.submit_button) + BUTTON_POSTFIX)

        signUpButton = hasContentDescription(rule.activity.getString(R.string.sign_up_button) + BUTTON_POSTFIX)

        emailTextField = hasContentDescription(rule.activity.getString(R.string.email))

        passwordTextField = hasContentDescription(rule.activity.getString(R.string.password))
    }


    @Test
    fun `check default state of the sign up screen`() {
        rule.onNode(signUpButton).performClick()
        val pageTitle = hasText(rule.activity.getString(R.string.sign_up_screen_title))
        rule.onNode(pageTitle).assertExists()
        rule.onNode(bottomNavBar).assertDoesNotExist()
        rule.onNode(emailTextField).assertExists()
        rule.onNode(passwordTextField).assertExists()
        rule.onNode(submitButton).assertExists()
    }

    @Test
    fun `enter valid sign up details`(){
        rule.onNode(signUpButton).performClick()

        rule.onNode(emailTextField).performTextInput("newuser@email.com") //must be a valid email or firebase will put up an error via toast
        rule.onNode(passwordTextField).performTextInput("password")
        rule.onNode(submitButton).performClick()
    }

    //@Test
    fun `enter invalid sign up details`(){
        //tba
    }
}