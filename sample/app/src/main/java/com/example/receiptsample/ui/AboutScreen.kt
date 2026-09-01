package com.example.receiptsample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
  Column(
    modifier = modifier.fillMaxSize().padding(24.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("About", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.testTag("about-title"))
    Text("Receipt Sample 1.0", modifier = Modifier.testTag("about-version"))
    Text(
      "An agent does not get to say done until it leaves a receipt.",
      style = MaterialTheme.typography.bodyLarge,
      modifier = Modifier.testTag("about-thesis"),
    )
  }
}
