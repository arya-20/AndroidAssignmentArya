package com.example.bottomnav1.screens

import androidx.compose.ui.test.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder( MethodSorters.DEFAULT)
class WeightGainScreenTests : ScreenTests() {
    @Before
    override fun setUp() {
        super.setUp()
    }

    @Test
    fun `go to the weight gain screen`() {
        `log in`()
        rule.onNode(weightGainScreenButton).performClick()
    }

    @Test
    fun `check the default state of the weight gain screen`() {
        `go to the weight gain screen`()

        val pageTitle = hasText("Weight Gain Recipes")
        rule.onNode(pageTitle).assertExists()
        rule.onNode(weightGainScreenText).assertExists()
//        rule.onNode(recipeListItem).assertExists()
        rule.onNode(addButton).assertExists()
        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(homeNavBarItem).assertExists()
        rule.onNode(settingsNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
    }

    @Test
    fun `go to the add screen`() {
        `go to the weight gain screen`()
        rule.onNode(addButton).performClick()
        val pageTitle = hasText("Add ")
        rule.onNode(pageTitle).assertExists()
    }

    @Test
    fun `select a recipe and navigate to the recipe detail screen`() {
        `go to the weight gain screen`()

        rule.onNode(recipeItem).performClick()
        rule.onNode(recipeDetailText).assertExists()
    }

}