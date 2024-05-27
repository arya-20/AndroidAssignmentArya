package com.example.bottomnav1.screens

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
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
    val settingsNavBarItem = hasText(NavScreen.Settings.route) and hasAnySibling(exitNavBarItem)

    //Data for add screen
    val EMAIL = "email"
    val PASSWORD = "password"
    val RECIPE = "recipe"
    val VALID_EMAIL = "test@gmail.com"
    val VALID_PASSWORD = "password"



    lateinit var submitButton : SemanticsMatcher
    //For start screen
    lateinit var appLogo: SemanticsMatcher
    lateinit var startScreenText : SemanticsMatcher
    lateinit var startScreenTitle : SemanticsMatcher
    lateinit var startScreenTitle2 : SemanticsMatcher
    lateinit var startButton : SemanticsMatcher

    lateinit var recipeTextField: SemanticsMatcher
    lateinit var bulkScreenText : SemanticsMatcher
    lateinit var recipeDetailText : SemanticsMatcher


    lateinit var addButton : SemanticsMatcher
    lateinit var editButton : SemanticsMatcher
    lateinit var trackButton : SemanticsMatcher
    lateinit var unitToggle : SemanticsMatcher

    lateinit var currentWeightTextField : SemanticsMatcher
    lateinit var targetWeightTextField : SemanticsMatcher



    //For home screen
    val listItem = hasText("$EMAIL $PASSWORD $RECIPE")
    lateinit var homeScreenText : SemanticsMatcher
    lateinit var deleteButton: SemanticsMatcher
    lateinit var bulkPrepScreenButton: SemanticsMatcher
    lateinit var weightLossScreenButton: SemanticsMatcher
    lateinit var weightGainScreenButton: SemanticsMatcher
    lateinit var veganScreenButton: SemanticsMatcher
    lateinit var trackScreenButton: SemanticsMatcher


    //For login + sign up screen
    lateinit var loginTitle : SemanticsMatcher
    lateinit var emailTextField : SemanticsMatcher
    lateinit var passwordTextField : SemanticsMatcher
    lateinit var forgotPasswordButton : SemanticsMatcher
    lateinit var signUpButton : SemanticsMatcher
    lateinit var backButton: SemanticsMatcher
    lateinit var signUpScreenTitle: SemanticsMatcher


    //For edit screen
    lateinit var editScreenText : SemanticsMatcher

    @Before
    open fun setUp() {
        val BUTTON_POSTFIX = " button"
        appLogo = hasContentDescription("Logo")
        forgotPasswordButton = hasContentDescription( rule.activity.getString(R.string.forgot_password) )
        submitButton = hasContentDescription(rule.activity.getString(R.string.submit_button) )
        signUpButton = hasContentDescription(rule.activity.getString(R.string.sign_up_button) ) //REMOVE THE BUTTON POSTFIXES
        backButton = hasContentDescription(rule.activity.getString(R.string.back_button) + BUTTON_POSTFIX)
        deleteButton = hasContentDescription(rule.activity.getString(R.string.delete) + BUTTON_POSTFIX)
        addButton = hasContentDescription(rule.activity.getString(R.string.add) + BUTTON_POSTFIX)
        trackButton = hasContentDescription(rule.activity.getString(R.string.track))
        unitToggle = hasContentDescription("weightUnit")

        bulkPrepScreenButton = hasContentDescription("Bulk Prep")
        weightLossScreenButton = hasContentDescription("Weight Loss")
        weightGainScreenButton = hasContentDescription("Weight Gain")
        veganScreenButton = hasContentDescription("Vegan")
        trackScreenButton = hasContentDescription("Track")

//        recipeItem = hasContentDescription("RecipeItem")


        startScreenTitle = hasText(rule.activity.getString(R.string.start_title))
        startScreenTitle2 = hasText(rule.activity.getString(R.string.start_title2))
        startButton = hasText(rule.activity.getString(R.string.start_button))and hasClickAction()

        loginTitle = hasText((rule.activity.getString(R.string.login_screen_title)))
        emailTextField = hasContentDescription(rule.activity.getString(R.string.email))
        passwordTextField = hasContentDescription(rule.activity.getString(R.string.password))
        recipeTextField = hasContentDescription(rule.activity.getString(R.string.recipe_hint))

        currentWeightTextField = hasContentDescription(rule.activity.getString(R.string.current_weight))
        targetWeightTextField = hasContentDescription(rule.activity.getString(R.string.target_weight))

        homeScreenText = hasText(rule.activity.getString(R.string.home))
        editScreenText = hasText(rule.activity.getString(R.string.edit))

    }

    //Use for valid and invalid sign ins - use default values for generic log in
    fun `log in`(email:String = "testuser@gmail.com", password: String = "password") {
        rule.onNode(emailTextField).printToLog("UI_TEST");
        rule.onNode(emailTextField).performTextInput(email)
        rule.onNode(passwordTextField).performTextInput(password)
        rule.onNode(submitButton).performClick()

        Thread.sleep(1000)
    }

    //Used by add screen + home screen creating a user before editing
    fun `enter_a_valid_user`(){
        rule.onNode(emailTextField).performTextInput(EMAIL)
        rule.onNode(passwordTextField).performTextInput(PASSWORD)
        rule.onNode(recipeTextField).performTextInput(RECIPE)

        rule.onNode(addButton).performClick()
    }


}