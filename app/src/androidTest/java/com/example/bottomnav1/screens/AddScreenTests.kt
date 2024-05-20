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
        `sign in`()
        rule.onNode(addNavBarItem).performClick()
    }

    @Test
    fun `check the default state of the add screen`() {
        `go to the add screen`()

        rule.onNode(addScreenText).assertExists()
        //Nav bar
        rule.onNode(bottomNavBar).assertExists()
        rule.onNode(homeNavBarItem).assertExists()
        rule.onNode(addNavBarItem).assertExists()
        rule.onNode(exitNavBarItem).assertExists()
    }

    @Test
    fun `add a valid contact then delete it from the list`() {
        `go to the add screen`()

        //enter valid data then submit
        `enter_a_valid_user`()

        //Should be auto returned to the home screen
        rule.onNode(homeScreenText).assertExists()

        //Check entry exists in list view
        rule.onNode(listItem).assertExists()
        //delete entry
         rule.onNode(listItem).performClick()
         rule.onNode(deleteButton).performClick()
    }

    //@Test
    fun `try to submit an invalid contact`(){

    }
}