package com.example.bottomnav1.screens

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.bottomnav1.core.MainActivity
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder( MethodSorters.DEFAULT)
class SampleLoginTests {
    @get:Rule
    var rule = createAndroidComposeRule<MainActivity>()

    private lateinit var emailAddressTextField : SemanticsMatcher
    private lateinit var passwordTextField : SemanticsMatcher
    private lateinit var submitButton : SemanticsMatcher
    private lateinit var forgotPasswordButton : SemanticsMatcher
    private lateinit var signUpButton : SemanticsMatcher

//    @Before
//    fun setUp() {
//        emailAddressTextField = hasText(rule.activity.getString(R.string.email))
//        passwordTextField = hasText(rule.activity.getString(R.string.password))
//        submitButton = hasText(rule.activity.getString(R.string.submit_button)) and hasClickAction()
//
//        forgotPasswordButton = hasText( rule.activity.getString(R.string.forgot_password)) and hasClickAction()
//        signUpButton = hasText(rule.activity.getString(R.string.sign_up_button)) and hasClickAction()
//    }

    @Test
    fun `check state of the login in page`() {
        rule.onNode(submitButton).assertExists()
        rule.onNode(forgotPasswordButton).assertExists()
        rule.onNode(signUpButton).assertExists()

        rule.onNode(emailAddressTextField).assertExists()
        rule.onNode(passwordTextField).assertExists()
    }
}