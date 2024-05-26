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

    private fun `go to the recipe details screen via bulk`() {
        `log in`()
        rule.onNode(bulkPrepScreenButton).performClick()
        rule.onNodeWithText("Bulk Preparation Recipes").assertExists()
        rule.onAllNodesWithContentDescription("RecipeItem")
//        rule.onAllNodesWithContentDescription("RecipeItem").first().performClick()
        rule.onNode(recipeDetailText).assertExists()

    }

    private fun `go to the recipe details screen via vegan`() {
        `log in`()
        rule.onNode(veganScreenButton).performClick()
        rule.onNodeWithText("Vegan Recipes").assertExists()
        rule.onAllNodesWithContentDescription("RecipeItem")
//        rule.onAllNodesWithContentDescription("RecipeItem").first().performClick()
        rule.onNode(recipeDetailText).assertExists()

    }

    private fun `go to the recipe details screen via weight loss`() {
        `log in`()
        rule.onNode(weightLossScreenButton).performClick()
        rule.onNodeWithText("Weight Loss Recipes").assertExists()
        rule.onAllNodesWithContentDescription("RecipeItem")
//        rule.onAllNodesWithContentDescription("RecipeItem").first().performClick()
        rule.onNode(recipeDetailText).assertExists()

    }

    private fun `go to the recipe details screen via weight gain`() {
        `log in`()
        rule.onNode(weightGainScreenButton).performClick()
        rule.onNodeWithText("Weight Gain Recipes").assertExists()
        rule.onAllNodesWithContentDescription("RecipeItem")
//        rule.onAllNodesWithContentDescription("RecipeItem").first().performClick()
        rule.onNode(recipeDetailText).assertExists()

    }


        @Test
    fun `check the default state of the recipe details screen`() {
        `go to the recipe details screen via bulk`()

        rule.onNode(recipeDetailText).assertExists()
        rule.onNode(editButton).assertExists()
        rule.onNode(deleteButton).assertExists()
        rule.onAllNodesWithContentDescription("RecipeItem")

        //Nav bar
        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(homeNavBarItem).assertExists()
        rule.onNode(settingsNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
    }

    @Test
    fun `go to the recipe details screen through bulk prep`() {
        `go to the recipe details screen via bulk`()
        rule.onNode(recipeDetailText).assertExists()
    }

    @Test
    fun `go to the recipe details screen through vegan`() {
        `go to the recipe details screen through vegan`()
        rule.onNode(recipeDetailText).assertExists()

    }

    @Test
    fun `go to the recipe details screen through weight loss`() {
        `go to the recipe details screen through weight loss`()
        rule.onNode(recipeDetailText).assertExists()

    }

    @Test
    fun `go to the recipe details screen through weight gain`() {
        `go to the recipe details screen through weight gain`()
        rule.onNode(recipeDetailText).assertExists()

    }
}