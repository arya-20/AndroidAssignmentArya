package com.example.bottomnav1.screens

import androidx.compose.ui.test.*
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder( MethodSorters.DEFAULT)
class WeightLossScreenTests : ScreenTests() {
    @Before
    override fun setUp() {
        super.setUp()
    }

    private fun `go to the weight loss screen`() {
        `log in`()
        rule.onNode(weightLossScreenButton).performClick()
    }

    @Test
    fun `check the default state of the weight loss screen`() {
        `go to the weight loss screen`()

        rule.onNodeWithText("weight loss Recipes").assertExists()
        rule.onNode(addButton).assertExists()
        //Nav bar
        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(homeNavBarItem).assertExists()
        rule.onNode(settingsNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
    }


    @Test
    fun `select a recipe and navigate to the recipe detail screen`() {
        `go to the weight loss screen`()

        rule.onAllNodesWithContentDescription("RecipeItem")
        rule.onNode(recipeDetailText).assertExists()
    }

    @Test
    fun `check if the list of recipes is displayed`() {
        `go to the weight loss screen`()

        rule.onAllNodesWithContentDescription("RecipeItem")
    }

}
