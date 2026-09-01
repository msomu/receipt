package com.example.receiptsample.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.example.receiptsample.domain.Counter
import com.example.receiptsample.theme.ReceiptSampleTheme

enum class HomeTab(val label: String, val tag: String) {
  Counter("Counter", "tab-counter"),
  Settings("Settings", "tab-settings"),
  About("About", "tab-about"),
}

@Composable
fun HomeScreen() {
  var tab by rememberSaveable { mutableStateOf(HomeTab.Counter) }
  var dark by rememberSaveable { mutableStateOf(false) }
  var count by rememberSaveable { mutableIntStateOf(0) }

  ReceiptSampleTheme(darkTheme = dark, dynamicColor = false) {
    Scaffold(
      bottomBar = {
        NavigationBar {
          HomeTab.entries.forEach { dest ->
            NavigationBarItem(
              selected = tab == dest,
              onClick = { tab = dest },
              icon = { Text(dest.label.take(1)) },
              label = { Text(dest.label) },
              modifier =
                Modifier.testTag(dest.tag).semantics { contentDescription = dest.label + " tab" },
            )
          }
        }
      }
    ) { padding ->
      val body = Modifier.padding(padding)
      when (tab) {
        HomeTab.Counter -> {
          val counter = Counter(count)
          CounterScreen(
            value = counter.value,
            onIncrement = { count = counter.increment().value },
            onDecrement = { count = counter.decrement().value },
            onReset = { count = counter.reset().value },
            modifier = body,
          )
        }
        HomeTab.Settings ->
          SettingsScreen(dark = dark, onDarkChange = { dark = it }, modifier = body)
        HomeTab.About -> AboutScreen(modifier = body)
      }
    }
  }
}
