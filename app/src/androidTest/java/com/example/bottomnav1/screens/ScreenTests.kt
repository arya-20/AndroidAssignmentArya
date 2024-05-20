package com.example.bottomnav1.screens

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasNoClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.bottomnav1.R
import com.example.bottomnav1.core.MainActivity
import com.example.bottomnav1.presentation.navigation.NavScreen
import org.junit.Before
import org.junit.Rule

open abstract class ScreenTests {
    @get:Rule
    var rule = createAndroidComposeRule<MainActivity>()

    //Nav bar items
    val bottomNavBar = hasContentDescription("bottom_nav")
    val exitNavBarItem = hasText(NavScreen.Exit.route)
    val homeNavBarItem = hasText(NavScreen.Home.route) and hasAnySibling(exitNavBarItem)
    val addNavBarItem = hasText(NavScreen.Add.route) and hasAnySibling(exitNavBarItem)
    val settingsNavBarItem = hasText(NavScreen.Settings.route) and hasAnySibling(exitNavBarItem)

    lateinit var submitButton : SemanticsMatcher

    //Data for add screen
    val EMAIL = "email"
    val PASSWORD = "password"
    val RECIPE = "recipe"
    val VALID_EMAIL = "newuser@email.com"
    val VALID_PASSWORD = "password"


    lateinit var recipeTextField: SemanticsMatcher
    lateinit var addScreenText : SemanticsMatcher
    lateinit var addButton : SemanticsMatcher

    //For home screen
    val listItem = hasText("$EMAIL $PASSWORD $RECIPE")
    lateinit var homeScreenText : SemanticsMatcher
    lateinit var forgotPasswordButton : SemanticsMatcher
    lateinit var signUpButton : SemanticsMatcher
    lateinit var deleteButton: SemanticsMatcher

    //For login + sign up screen
    lateinit var emailTextField : SemanticsMatcher
    lateinit var passwordTextField : SemanticsMatcher
    lateinit var backButton: SemanticsMatcher

    //For edit screen
    lateinit var editScreenText : SemanticsMatcher

    @Before
    open fun setUp() {
        val BUTTON_POSTFIX = " button"
        forgotPasswordButton = hasContentDescription( rule.activity.getString(R.string.forgot_password) + BUTTON_POSTFIX)
        submitButton = hasContentDescription(rule.activity.getString(R.string.submit_button) + BUTTON_POSTFIX)
        signUpButton = hasContentDescription(rule.activity.getString(R.string.sign_up_button) + BUTTON_POSTFIX)
        backButton = hasContentDescription(rule.activity.getString(R.string.back_button) + BUTTON_POSTFIX)
        deleteButton = hasContentDescription(rule.activity.getString(R.string.delete) + BUTTON_POSTFIX)
        addButton = hasContentDescription(rule.activity.getString(R.string.add) + " button")

        emailTextField = hasContentDescription(rule.activity.getString(R.string.email))
        passwordTextField = hasContentDescription(rule.activity.getString(R.string.password))

        emailTextField = hasContentDescription(rule.activity.getString(R.string.email_hint))
        passwordTextField = hasContentDescription(rule.activity.getString(R.string.password_hint))
        recipeTextField = hasContentDescription(rule.activity.getString(R.string.recipe_hint))

        homeScreenText = hasText(rule.activity.getString(R.string.home))
        addScreenText = hasText(rule.activity.getString(R.string.add)) and hasNoClickAction()
        editScreenText = hasText(rule.activity.getString(R.string.edit))
    }

    //Use for valid and invalid sign ins - use default values for generic log in
    fun `sign in`(email:String = "newuser@email.com", password: String = "password") {
        //rule.onNode(emailAddressTextField).printToLog("UI_TEST");
        rule.onNode(emailTextField).performTextInput(email)
        rule.onNode(passwordTextField).performTextInput(password)
        rule.onNode(submitButton).performClick()

        Thread.sleep(1000)//pause or the following will fail - recommendation is an idle call back (not demonstrated here)
    }

    //Used by add screen + home screen creating a user before editing
    fun `enter_a_valid_user`(){
        rule.onNode(emailTextField).performTextInput(EMAIL)
        rule.onNode(passwordTextField).performTextInput(PASSWORD)
        rule.onNode(recipeTextField).performTextInput(RECIPE)

        rule.onNode(addButton).performClick()
    }
}