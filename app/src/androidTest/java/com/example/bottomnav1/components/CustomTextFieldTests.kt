package com.example.bottomnav1.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.bottomnav1.presentation.components.CustomTextField
import com.example.bottomnav1.presentation.theme.BottomNav1Theme
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters



@FixMethodOrder( MethodSorters.DEFAULT)
class CustomTextFieldTests {
    private val HINT_TEXT = "hint text"
    private val TEXT_TO_BE_DISPLAYED = "text"
    private var textInput = ""
    private val ERROR_MESSAGE_TEXT = "error displayed"
    private var errorIsNotPresent = true

    @get:Rule
    var rule = createComposeRule()

    @Test
    fun `check default state of the textfield with text present`() {
        rule.setContent {
            BottomNav1Theme {
                CustomTextField(
                    hintText = HINT_TEXT,
                    text = TEXT_TO_BE_DISPLAYED,
                    isPasswordField = false,
                    onValueChange = { textInput = it },
                    errorMessage = ERROR_MESSAGE_TEXT,
                    errorPresent = !errorIsNotPresent
                )
            }
        }

        rule.onNodeWithText(HINT_TEXT).assertExists()
        rule.onNodeWithText(TEXT_TO_BE_DISPLAYED).assertExists()
        rule.onNodeWithText(ERROR_MESSAGE_TEXT).assertDoesNotExist()
        assertTrue(errorIsNotPresent)
    }

    @Test
    fun `check state of the textfield with additional valid text input`() {
        rule.setContent {
            BottomNav1Theme {
                CustomTextField(
                    hintText = HINT_TEXT,
                    text = TEXT_TO_BE_DISPLAYED,
                    isPasswordField = false,
                    onValueChange = { textInput = it },
                    errorMessage = ERROR_MESSAGE_TEXT,
                    errorPresent = !errorIsNotPresent
                )
            }
        }

        val ADDITIONAL_TEXT = "something"
        rule.onNodeWithText(HINT_TEXT).assertExists()
        rule.onNodeWithText(TEXT_TO_BE_DISPLAYED).performTextInput(ADDITIONAL_TEXT)
        rule.onNodeWithText(ERROR_MESSAGE_TEXT).assertDoesNotExist()
        assertTrue(errorIsNotPresent)
        assertEquals(ADDITIONAL_TEXT + TEXT_TO_BE_DISPLAYED, textInput)
    }

    @Test
    fun `textfield with error present state set should display an error_message`() {
        rule.setContent {
            BottomNav1Theme {
                CustomTextField(
                    hintText = HINT_TEXT,
                    text = "",
                    isPasswordField = false,
                    onValueChange = { textInput = it },
                    errorMessage = ERROR_MESSAGE_TEXT,
                    errorPresent = true
                )
            }
        }

        rule.onNodeWithText(HINT_TEXT).assertExists()
        rule.onNodeWithText(ERROR_MESSAGE_TEXT).assertExists()
    }

    @Test
    fun `Textfield should display asterisks when set to password`() {
        val inputText = "test"
        val expectedAsterisks = "••••"

        rule.setContent {
            BottomNav1Theme {
                CustomTextField(
                    hintText = HINT_TEXT,
                    text = inputText,
                    isPasswordField = true,
                    onValueChange = { textInput = it },
                    errorMessage = ERROR_MESSAGE_TEXT,
                    errorPresent = false
                )
            }
        }

        rule.onNodeWithText(expectedAsterisks)
            .assertExists()
    }
}