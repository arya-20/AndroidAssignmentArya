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
    val INGREDIENTS = "ingredients testing example"
    val INSTRUCTIONS = "instructions testing example"
    val CATEGORY = "category testing example"

    val VALID_EMAIL = "testuser@gmail.com"
    val VALID_PASSWORD = "password"
    val INVALID_EMAIL = "kjdfkhadfhks"
    val INVALID_PASSWORD = "dsifhkjds"

    val recipeListItem = ("$RECIPE")

    lateinit var submitButton : SemanticsMatcher
    //For start screen
    lateinit var appLogo: SemanticsMatcher
    lateinit var startScreenTitle : SemanticsMatcher
    lateinit var startScreenTitle2 : SemanticsMatcher
    lateinit var startButton : SemanticsMatcher

    lateinit var recipeTextField: SemanticsMatcher
    lateinit var bulkScreenText : SemanticsMatcher
    lateinit var veganScreenText : SemanticsMatcher
    lateinit var weightGainScreenText : SemanticsMatcher
    lateinit var weightLossScreenText : SemanticsMatcher

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
    lateinit var recipeList : SemanticsMatcher
    lateinit var recipeItem : SemanticsMatcher
    lateinit var nameField : SemanticsMatcher
    lateinit var categoryField : SemanticsMatcher
    lateinit var ingredientsField : SemanticsMatcher
    lateinit var instructionsField : SemanticsMatcher


    @Before
    open fun setUp() {
        appLogo = hasContentDescription("Logo")
        forgotPasswordButton = hasContentDescription( rule.activity.getString(R.string.forgot_password) ) and hasClickAction()
        submitButton = hasContentDescription(rule.activity.getString(R.string.submit_button)) and hasClickAction()
        signUpButton = hasContentDescription(rule.activity.getString(R.string.sign_up_button)) and hasClickAction()
        backButton = hasContentDescription(rule.activity.getString(R.string.back_button)) and hasClickAction()
        deleteButton = hasContentDescription(rule.activity.getString(R.string.delete)) and hasClickAction()
        addButton = hasContentDescription(rule.activity.getString(R.string.add)) and hasClickAction()
        trackButton = hasText(rule.activity.getString(R.string.track)) and hasClickAction()
        unitToggle = hasContentDescription("weightUnit") and hasClickAction()

        bulkPrepScreenButton = hasContentDescription(rule.activity.getString(R.string.bulk_prep)) and hasClickAction()
        weightLossScreenButton = hasContentDescription(rule.activity.getString(R.string.weight_loss)) and hasClickAction()
        weightGainScreenButton = hasContentDescription(rule.activity.getString(R.string.weight_gain)) and hasClickAction()
        veganScreenButton = hasContentDescription(rule.activity.getString(R.string.vegan)) and hasClickAction()
        trackScreenButton = hasContentDescription(rule.activity.getString(R.string.track)) and hasClickAction()
        recipeList = hasContentDescription("Recipe List") and hasClickAction()
        recipeItem = hasText("recipe") and hasClickAction()


        startScreenTitle = hasText(rule.activity.getString(R.string.start_title))
        startScreenTitle2 = hasText(rule.activity.getString(R.string.start_title2))
        startButton = hasText(rule.activity.getString(R.string.start_button))and hasClickAction()

        loginTitle = hasText((rule.activity.getString(R.string.login_screen_title)))
        emailTextField = hasText(rule.activity.getString(R.string.email))
        passwordTextField = hasText(rule.activity.getString(R.string.password))

        recipeTextField = hasContentDescription(rule.activity.getString(R.string.recipe_hint))
        currentWeightTextField = hasText("Current Weight")
        targetWeightTextField = hasText("Target Weight")
        homeScreenText = hasText(rule.activity.getString(R.string.home))
        editScreenText = hasText(rule.activity.getString(R.string.edit))
        bulkScreenText = hasText(rule.activity.getString(R.string.bulk_prep_text))
        veganScreenText = hasText(rule.activity.getString(R.string.vegan_screen_text))
        weightGainScreenText = hasText(rule.activity.getString(R.string.weight_gain_text))
        weightLossScreenText = hasText(rule.activity.getString(R.string.weight_loss_text))

        recipeDetailText = hasText("Recipe Details")
        nameField = hasText(rule.activity.getString(R.string.name))
        categoryField = hasText(rule.activity.getString(R.string.category))
        ingredientsField = hasText(rule.activity.getString(R.string.ingredients))
        instructionsField = hasText(rule.activity.getString(R.string.instructions))

    }

    fun `move to login`(){
        rule.onNode(startButton).performClick()
    }

    fun `log in`(email:String = "testuser@gmail.com", password: String = "password") {
        `move to login`()
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

    fun `go to bulk`(){      //to reach the add page
        `log in`()
        rule.onNode(bulkPrepScreenButton).performClick()
    }


}