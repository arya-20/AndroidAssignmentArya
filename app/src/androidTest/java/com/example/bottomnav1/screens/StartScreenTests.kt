package com.example.bottomnav1.screens

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import com.example.bottomnav1.R
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder( MethodSorters.DEFAULT)
class StartScreenTests : ScreenTests() {

    //For start screen
    lateinit var appLogo: SemanticsMatcher
    lateinit var startScreenText : SemanticsMatcher
    lateinit var startScreenTitle : SemanticsMatcher
    lateinit var startScreenTitle2 : SemanticsMatcher
    lateinit var startButton : SemanticsMatcher


    @Before
    override fun setUp() {
//        loginScreenTitle = hasText(rule.activity.getString(R.string.login_screen_title))
        appLogo = hasContentDescription("Logo")
        startScreenTitle = hasText(rule.activity.getString(R.string.start_title))
        startScreenTitle2 = hasText(rule.activity.getString(R.string.start_title2))
        startButton = hasText(rule.activity.getString(R.string.start_button))and hasClickAction()

    }

//    private fun `go to the login screen`() {
//        rule.onNodeWithText("Start").performClick()


        @Test
        fun `check the default state of the start screen`() {
            rule.onNode(appLogo).assertExists()
            rule.onNode(startScreenTitle).assertExists()
            rule.onNode(startScreenTitle2).assertExists()
            rule.onNode(startButton).assertExists()

        }

        @Test
        fun `move to the login page`(){
            rule.onNode(startButton).performClick()
            //on login page
            val pageTitle =
                hasText(rule.activity.getString(R.string.login_screen_title))
            rule.onNode(pageTitle).assertExists()
        }

}