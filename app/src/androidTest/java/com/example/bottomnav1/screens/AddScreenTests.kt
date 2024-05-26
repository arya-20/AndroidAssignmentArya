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
        `log in`()
        rule.onNode(addButton).performClick()
    }

    @Test
    fun `check the default state of the add screen`() {
        `go to the add screen`()

        rule.onNodeWithText("name").assertExists()
        rule.onNodeWithText("Category").assertExists()
        rule.onNodeWithText("Ingredients").assertExists()
        rule.onNodeWithText("Instructions").assertExists()
        rule.onNode(addButton).assertExists()
    }

    @Test
    fun `check the add button navigates back to home`() {
        `go to the add screen`()

    }
}