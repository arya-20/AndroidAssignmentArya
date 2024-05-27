package com.example.bottomnav1.screens

import androidx.compose.ui.test.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder( MethodSorters.DEFAULT)
class AddScreenTests : ScreenTests() {
    @Before
    override fun setUp() {
        super.setUp()
    }

    private fun `go to the add screen`() {
        `go to bulk`()
        rule.onNode(addButton).performClick()
    }

    @Test
    fun `check the default state of the add screen`() {
        `go to the add screen`()

        rule.onNode(nameField).assertExists()
//        rule.onNode(categoryField).assertExists()
        rule.onNode(ingredientsField).assertExists()
        rule.onNode(instructionsField).assertExists()
        rule.onNode(addButton).assertExists()

        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(homeNavBarItem).assertExists()
        rule.onNode(settingsNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
    }
}