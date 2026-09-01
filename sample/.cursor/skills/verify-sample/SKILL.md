---
name: verify-sample
description: "Drive Receipt Sample (Compose Android). Use when proving a change to the sample app: doctor, unit tests, assembleDebug, adb screenshot, logcat."
---

# verify-sample

Talk demo app for [receipt](https://github.com/msomu/receipt). Three tabs: Counter, Settings, About.

## Launch

```
cd sample
./gradlew :app:assembleDebug
./verify screenshot
```

Ready = the activity `com.example.receiptsample.MainActivity` is in the foreground and `counter-value` reads `0`. Teardown = `adbharbor release -s <serial>` if this run acquired a lease. Never `--force`.

## Doctor

```
./verify doctor
./verify --dry-run doctor
```

Healthy: `java`, `adb`, `gradlew`, and `ok: true` in the JSON. Device is optional for `test` / `assemble`. Required for `screenshot` / `logcat`.

## Drive

Tags: `tab-counter`, `tab-settings`, `tab-about`, `counter-value`, `increment`, `decrement`, `reset`, `dark-mode`, `about-version`.

```
adb -s $SERIAL shell uiautomator dump /sdcard/window_dump.xml
```

Tap by `contentDescription`: `Increment`, `Decrement`, `Reset`, `Counter tab`, `Settings tab`, `About tab`, `Dark mode`.

## Evidence

`.receipt/proof/<utc>/` — `test_task.log`, `assemble_task.log`, `screen.png`, `logcat.txt`. Cleanup must not delete this directory.

## Cleanup

Release a lease this CLI acquired. Leave proof files. Do not uninstall the app unless you installed it for this run and the human asked.

## Helpers

```
./verify --help
./verify doctor
./verify test
./verify assemble
./verify screenshot
./verify logcat --lines 200
```

Pin a serial with `./verify --device emulator-5554 screenshot` or `ANDROID_SERIAL`.
