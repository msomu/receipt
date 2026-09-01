package com.example.receiptsample.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class CounterTest {
  @Test
  fun increment_addsOne() {
    assertEquals(1, Counter(0).increment().value)
  }

  @Test
  fun decrement_floorsAtZero() {
    assertEquals(0, Counter(0).decrement().value)
  }

  @Test
  fun reset_returnsZero() {
    assertEquals(0, Counter(4).reset().value)
  }
}
