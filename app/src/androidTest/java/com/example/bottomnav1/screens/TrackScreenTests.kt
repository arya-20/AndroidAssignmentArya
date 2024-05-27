package com.example.bottomnav1.screens

import androidx.compose.ui.test.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder( MethodSorters.DEFAULT)
class TrackScreenTests : ScreenTests() {
    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
     fun `go to the track screen`() {
        `log in`()
        rule.onNode(trackScreenButton).performClick()
    }

    @Test
    fun `check the default state of the track screen`() {
        `go to the track screen`()
        val pageTitle = hasText("Track Progress")
        rule.onNode(pageTitle).assertExists()
        rule.onNode(currentWeightTextField).assertExists()
        rule.onNode(targetWeightTextField).assertExists()
        rule.onNode(unitToggle).assertExists()
        rule.onNode(trackButton).assertExists()

        //Nav bar
        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(homeNavBarItem).assertExists()
        rule.onNode(settingsNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
    }

}
