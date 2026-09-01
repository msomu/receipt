package com.example.receiptsample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp

@Composable
fun CounterScreen(
  value: Int,
  onIncrement: () -> Unit,
  onDecrement: () -> Unit,
  onReset: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier.fillMaxSize().padding(24.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text("Counter", style = MaterialTheme.typography.headlineMedium)
    Text(
      text = value.toString(),
      style = MaterialTheme.typography.displayLarge,
      modifier = Modifier.testTag("counter-value").semantics { contentDescription = "Counter value $value" },
    )
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
      Button(
        onClick = onDecrement,
        modifier = Modifier.testTag("decrement").semantics { contentDescription = "Decrement" },
      ) {
        Text("Decrement")
      }
      Button(
        onClick = onIncrement,
        modifier = Modifier.testTag("increment").semantics { contentDescription = "Increment" },
      ) {
        Text("Increment")
      }
    }
    Button(
      onClick = onReset,
      modifier = Modifier.testTag("reset").semantics { contentDescription = "Reset" },
    ) {
      Text("Reset")
    }
  }
}
