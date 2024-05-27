package com.example.bottomnav1.screens

import androidx.compose.ui.test.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder( MethodSorters.NAME_ASCENDING)
class HomeScreenTests : ScreenTests(){
    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun `check default state of the home screen`() {
        `log in`()
        rule.onNode(homeScreenText).assertExists()
        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(settingsNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
        rule.onNode(homeNavBarItem).assertExists()
        rule.onNode(bulkPrepScreenButton).assertExists()
        rule.onNode(weightGainScreenButton).assertExists()
        rule.onNode(weightLossScreenButton).assertExists()
        rule.onNode(veganScreenButton).assertExists()
        rule.onNode(trackScreenButton).assertExists()

    }

    @Test
    fun `go to the settings screen`() {
        `log in`()
        rule.onNode(settingsNavBarItem).performClick()
        val pageTitle = hasText("Settings ")
        rule.onNode(pageTitle).assertExists()
        rule.onNode(bottomNavBar).assertExists()
    }

    @Test
    fun `logout`(){
        `log in`()
        rule.onNode(exitNavBarItem).performClick()
    }

    @Test
    fun `go to bulk screen`(){
        `log in`()
        rule.onNode(bulkPrepScreenButton).performClick()
        val pageTitle = hasText("Bulk Preparation Recipes")
        rule.onNode(pageTitle).assertExists()
        rule.onNode(bottomNavBar).assertExists()

    }

    @Test
    fun `go to weight loss screen`(){
        `log in`()
        rule.onNode(weightLossScreenButton).performClick()
        val pageTitle = hasText("Weight Loss Recipes")
        rule.onNode(pageTitle).assertExists()
        rule.onNode(bottomNavBar).assertExists()
    }

    @Test
    fun `go to weight gain screen`(){
        `log in`()
        rule.onNode(weightGainScreenButton).performClick()
        val pageTitle = hasText("Weight Gain Recipes")
        rule.onNode(pageTitle).assertExists()
        rule.onNode(bottomNavBar).assertExists()
    }

    @Test
    fun `go to vegan screen`(){
        `log in`()
        rule.onNode(veganScreenButton).performClick()
        val pageTitle = hasText("Vegan Recipes")
        rule.onNode(pageTitle).assertExists()
        rule.onNode(bottomNavBar).assertExists()


    }

    @Test
    fun `go to track screen`(){
        `log in`()
        rule.onNode(trackScreenButton).performClick()
        val pageTitle = hasText("Track Progress")
        rule.onNode(pageTitle).assertExists()
        rule.onNode(bottomNavBar).assertExists()

    }
}