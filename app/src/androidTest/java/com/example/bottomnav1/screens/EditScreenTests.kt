package com.example.bottomnav1.screens

import org.junit.Before
import org.junit.Test

class EditScreenTests: ScreenTests() {
    @Before
    override fun setUp() {
        super.setUp()
    }

    // route to edit screen: start > login > home > bulk > details > edit
    fun `go to the edit screen`() {

    }

    @Test
    fun `check the default state of the edit screen`() {
        `go to the edit screen`()
        rule.onNode(nameField).assertExists()
        rule.onNode(ingredientsField).assertExists()
        rule.onNode(instructionsField).assertExists()
        rule.onNode(editButton).assertExists()

        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(homeNavBarItem).assertExists()
        rule.onNode(settingsNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
    }
}

