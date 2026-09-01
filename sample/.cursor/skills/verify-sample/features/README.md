# Receipt Sample feature map

Sweep top to bottom. Drive from the user path. Proof lands in `sample/.receipt/proof/`.

## Baseline

- `cd sample && ./verify doctor` reports `ok: true`.
- APK from `:app:assembleDebug` at `app/build/outputs/apk/debug/app-debug.apk`.
- Pin `adb -s`. If `adbharbor` is present, `screenshot` / `logcat` take a lease and never `--force`.
- Start on the Counter tab with value `0`.

## Features

- [Counter](./counter.md) — increment, decrement floor, reset.
- [Settings](./settings.md) — dark mode switch.
- [About](./about.md) — version string and thesis line.
- [Tabs](./tabs.md) — move between the three destinations.
