package com.example.receiptsample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(dark: Boolean, onDarkChange: (Boolean) -> Unit, modifier: Modifier = Modifier) {
  Column(
    modifier = modifier.fillMaxSize().padding(24.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("Settings", style = MaterialTheme.typography.headlineMedium)
    Text(if (dark) "Dark mode on" else "Dark mode off", modifier = Modifier.testTag("dark-mode-label"))
    Switch(
      checked = dark,
      onCheckedChange = onDarkChange,
      modifier = Modifier.testTag("dark-mode").semantics { contentDescription = "Dark mode" },
    )
  }
}
