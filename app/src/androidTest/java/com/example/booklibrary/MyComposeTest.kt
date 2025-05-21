package com.example.booklibrary

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.booklibrary.ui.ErrorDialog
import com.example.booklibrary.ui.theme.BookLibraryTheme
import org.junit.Rule
import org.junit.Test

class MyComposeTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myTest() {
        composeTestRule.setContent {
            BookLibraryTheme {
                ErrorDialog(
                    message = "Error"
                ){}
            }
        }
        composeTestRule.onNodeWithText("Cancel").performClick()
    }
}