package com.example.bottomnav1.screens

import androidx.compose.ui.test.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder( MethodSorters.DEFAULT)
    class RecipeDetailsScreenTests : ScreenTests() {


    @Before
    override fun setUp() {
        super.setUp()
    }

    //path to this screen: start > login > home > bulk > select recipe

    @Test
    private fun `go to the details screen`() {
        `go to bulk`()
        rule.onNode(recipeItem).performClick()

    }

    @Test
    fun `check the default state of the recipe details screen`() {
        `go to the details screen`()

        rule.onNode(recipeDetailText).assertExists()
        rule.onNode(nameField).assertExists()
//        rule.onNode(categoryField).assertExists()
        rule.onNode(ingredientsField).assertExists()
        rule.onNode(instructionsField).assertExists()

        rule.onNode(editButton).assertExists()
        rule.onNode(deleteButton).assertExists()

        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(homeNavBarItem).assertExists()
        rule.onNode(settingsNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
    }

    @Test
    fun `go to the edit screen`() {
        `go to the details screen`()
        rule.onNode(editButton).performClick()
        val pageTitle = hasText("Edit ")
        rule.onNode(pageTitle).assertExists()
    }
}