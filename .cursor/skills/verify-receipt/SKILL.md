---
name: verify-receipt
description: "Drive the receipt plugin verify CLI (argv harness) against the bundled Receipt Sample Android app. Use when proving changes to the receipt plugin, its verify script, or agent receipt workflows: doctor, Gradle test/assemble, adb screenshot, logcat."
---

# verify-receipt

[receipt](https://github.com/msomu/receipt) is a Cursor plugin: tools first, markdown second. The primary surface is the **`verify` CLI** at the repo root — argv JSON on stdout, proof files on disk. The bundled **Receipt Sample** Compose Android app (`sample/`) is the proof target. Other surfaces: Cursor slash commands (`/receipt`, `/create-receipt`, `/maintain-receipt`), plugin skills under `skills/`, and the sample app's on-device UI (driven via adb after `screenshot --install --launch`).

Only one adb server may bind `:5037`. Do not run two harbor leases on the same serial. Gradle daemons are shared; two `assemble` runs in parallel on the same checkout contend for the same daemon.

## Launch

```bash
cd /path/to/receipt
export ANDROID_HOME="${ANDROID_HOME:-$HOME/android-sdk}"   # required for Gradle; set sdk.dir in sample/local.properties if needed
./verify --help
```

Ready signal: `--help` exits 0 and lists `doctor`, `test`, `assemble`, `screenshot`, `logcat`. The harness resolves `sample/` as the Android project root (looks for `sample/gradlew`). Teardown: nothing stays running after a single `verify` invocation. If you started an emulator or `adb` forward for a manual drive, stop only that process (by PID you recorded). Never `killall adb`.

## Doctor

```bash
./verify doctor
./verify --dry-run doctor
```

Healthy: JSON has `"ok": true` with `java`, `adb`, and `gradlew` paths present (`checks.gradlew` points at `sample/gradlew`). Device is optional for `test` and `assemble`. For `screenshot` / `logcat`: a local device/emulator, **or** `adbharbor submit` when harbor is on PATH and doctor reports no device. Cloud with harbor MCP: `wait_for_run` + `get_proof` (this skill ships no harbor URL).

## Drive

Subcommands map to [features/](./features/). Gradle tasks: `:app:testDebugUnitTest`, `:app:assembleDebug`. APK: `sample/app/build/outputs/apk/debug/app-debug.apk`. Package: `com.example.receiptsample`. Activity: `com.example.receiptsample.MainActivity`. Compose test tags on device: `tab-counter`, `tab-settings`, `tab-about`, `counter-value`, `increment`, `decrement`, `reset`, `dark-mode`, `about-version`. Tap by `contentDescription`: `Increment`, `Decrement`, `Reset`, `Counter tab`, `Settings tab`, `About tab`, `Dark mode`.

```bash
adb -s $SERIAL shell uiautomator dump /sdcard/window_dump.xml
adb -s $SERIAL shell input tap <x> <y>   # after reading dump for bounds
```

Pin serial: `./verify --device emulator-5554 test` or `ANDROID_SERIAL`.

## Evidence

`sample/.receipt/proof/<utc-run-id>/` — `test_task.log`, `assemble_task.log`, `test-results/` (from `test`), `screen.png`, `logcat.txt`. The JSON `proof` field names the directory. Cleanup must not delete `.receipt/proof/`.

## Cleanup

Release an AdbHarbor lease only if this run acquired one (`adbharbor release -s <serial>`; never `--force`). Leave all proof directories. Remove verification scaffolding you added (e.g. `sample/local.properties` written only for this run). Do not uninstall the sample APK unless you installed it in this run and were asked to.

## Helpers

```bash
./verify --help
./verify --dry-run test
./verify doctor
./verify test
./verify assemble
./verify screenshot
./verify screenshot --install --launch
./verify logcat --lines 200
```

`screenshot` is capture-only; `--install` and `--launch` are opt-in. `ok` is true only when every step exits 0 and `screen.png` starts with PNG magic (for screenshot). `logcat` filters to the app pid; it is not ok if the package is not running.

Keep the feature map honest with `/maintain-receipt`.
