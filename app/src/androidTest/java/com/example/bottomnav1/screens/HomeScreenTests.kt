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

//    @Test
//    fun `go to bulk screen`(){
//        `sign in`()
//        //Add a contact to view
//        rule.onNode(addNavBarItem).performClick()
//        `enter_a_valid_user`()
//        //select and edit the contact
//        rule.onNode(listItem).performClick()
//        //Check on the edit screen
//        rule.onNode(editScreenText).assertExists()
//
//        rule.onNode(homeNavBarItem).performClick()
//        // Tidy up - Delete the contact
//        rule.onNode(listItem).performClick()
//        rule.onNode(deleteButton).performClick()
//    }
}