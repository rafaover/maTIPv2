package com.exercise.matipv2

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.exercise.matipv2.layout.MainLayout
import com.exercise.matipv2.ui.theme.MaTIPv2Theme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

// See [testing documentation](http://d.android.com/tools/testing).
class TipUITests {
    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            MaTIPv2Theme {
                MainLayout()
            }
        }
        composeTestRule.onNodeWithText("Bill Amount").performTextInput("10")
        composeTestRule.onNodeWithText("Tip Percentage").performTextInput("20")
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists()
    }
    @Test
    fun calculate_20_percent_tip_NoRound() {
        composeTestRule.setContent {
            MaTIPv2Theme {
                MainLayout()
            }
        }
        composeTestRule.onNodeWithText("Bill Amount").performTextInput("13")
        composeTestRule.onNodeWithText("Tip Percentage").performTextInput("20")
        val expectedTip = NumberFormat.getCurrencyInstance().format(2.60)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists()
    }
    @Test
    fun calculate_20_percent_tip_Rounded() {
        composeTestRule.setContent {
            MaTIPv2Theme {
                MainLayout()
            }
        }
        composeTestRule.onNodeWithText("Bill Amount").performTextInput("13")
        composeTestRule.onNodeWithText("Tip Percentage").performTextInput("20")
        composeTestRule.onNodeWithTag("RoundTheTipSwitch").performClick()
        val expectedTip = NumberFormat.getCurrencyInstance().format(3)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists()
    }
}

