package com.example.receiptsample.domain

data class Counter(val value: Int = 0) {
  init {
    require(value >= 0) { "counter cannot be negative" }
  }

  fun increment(): Counter = copy(value = value + 1)

  fun decrement(): Counter = copy(value = maxOf(0, value - 1))

  fun reset(): Counter = Counter(0)
}
