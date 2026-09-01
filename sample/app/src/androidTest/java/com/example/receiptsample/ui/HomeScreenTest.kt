package com.example.receiptsample.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
  @get:Rule val composeTestRule = createAndroidComposeRule<ComponentActivity>()

  @Before
  fun setup() {
    composeTestRule.setContent { HomeScreen() }
  }

  @Test
  fun increment_updatesValue() {
    composeTestRule.onNodeWithTag("counter-value").assertTextEquals("0")
    composeTestRule.onNodeWithTag("increment").performClick()
    composeTestRule.onNodeWithTag("counter-value").assertTextEquals("1")
  }

  @Test
  fun about_tab_showsThesis() {
    composeTestRule.onNodeWithTag("tab-about").performClick()
    composeTestRule.onNodeWithTag("about-version").assertTextEquals("Receipt Sample 1.0")
  }
}
