# Counter

The first tab shows an integer. Increment adds one. Decrement stops at zero. Reset returns to zero.

## What it does

- `counter-show` renders the current value in `counter-value`.
- `counter-inc` adds one.
- `counter-dec` subtracts one and will not go below 0.
- `counter-reset` sets 0 from any value.

## How a user opens it

- Launch the app. Counter is the default tab.
- Tap `Counter tab` from Settings or About.

## How verify drives it

Preconditions: app in foreground, Counter tab selected, value `0`.

- **Read.** `adb -s $SERIAL exec-out uiautomator dump` — node `Counter value 0` exists.
- **Increment.** Tap `Increment`. Value reads `1`.
- **Decrement.** Tap `Decrement`. Value reads `0`.
- **Floor.** Tap `Decrement` again. Value stays `0`.
- **Reset.** Increment twice, tap `Reset`. Value reads `0`.
- **Proof.** `./verify screenshot` then `./verify logcat`. `screen.png` shows the value you left.

JVM receipt for the same rules: `./verify test` copies `:app:testDebugUnitTest` (`CounterTest`).

## What usually lies

- A screenshot of `0` after increment is the launch screen, not a drive. Capture after the tap.
- Decrement on `0` looking unchanged is success. Do not treat it as a missed tap without a dump.
- Instrumented `HomeScreenTest` is not a substitute for a device screenshot in a talk demo.
