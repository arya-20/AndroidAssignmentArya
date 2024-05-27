package com.example.bottomnav1.screens

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.performClick
import com.example.bottomnav1.R
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder( MethodSorters.DEFAULT)
class StartScreenTests : ScreenTests() {


    @Before
    override fun setUp() {
        super.setUp()

    }
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
            val pageTitle = hasText(rule.activity.getString(R.string.login_screen_title))
            rule.onNode(pageTitle).assertExists()
        }

}