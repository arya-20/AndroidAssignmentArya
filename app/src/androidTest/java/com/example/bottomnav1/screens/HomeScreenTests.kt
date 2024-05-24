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
        `sign in`()
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
        `sign in`()
        rule.onNode(settingsNavBarItem).performClick()
    }

    @Test
    fun `logout`(){
        `sign in`()
        rule.onNode(exitNavBarItem).performClick()
    }

    @Test
    fun `go to bulk screen`(){
        `sign in`()
        rule.onNode(bulkPrepScreenButton).performClick()

    }

    @Test
    fun `go to weight loss screen`(){
        `sign in`()
        rule.onNode(weightLossScreenButton).performClick()

    }

    @Test
    fun `go to weight gain screen`(){
        `sign in`()
        rule.onNode(weightGainScreenButton).performClick()

    }

    @Test
    fun `go to vegan screen`(){
        `sign in`()
        rule.onNode(veganScreenButton).performClick()

    }

    @Test
    fun `go to track screen`(){
        `sign in`()
        rule.onNode(trackScreenButton).performClick()

    }
}