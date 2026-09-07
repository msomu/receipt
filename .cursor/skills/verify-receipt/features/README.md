# Receipt verify feature map

Sweep top to bottom. Drive from the user path. Proof lands in `sample/.receipt/proof/`.

## Baseline preconditions

- Repo root: `./verify --help` exits 0.
- `ANDROID_HOME` (or `ANDROID_SDK_ROOT`) set, or `sample/local.properties` with `sdk.dir=...`.
- `./verify doctor` reports `ok: true` (java + adb + gradlew).
- Record `git rev-parse HEAD` in the same shell as any Gradle receipt.

## Driving conventions

- Harness: `./verify` at repo root (argv; JSON on stdout).
- `--dry-run` lists commands and writes no proof.
- Pin adb serial with `--device` or `ANDROID_SERIAL`.
- If `adbharbor` is on PATH, `screenshot` / `logcat` take a lease and never `--force`.

## Proof / skip reporting

- **Proved**: name the feature, the exact command, and the `proof` path from JSON.
- **Skipped**: name the feature and why (no device, missing SDK, etc.) with the exact failing command and stderr snippet.
- Never claim a green run without a proof directory on disk.

## Feature index

- [doctor](./doctor.md) — toolchain health before any receipt.
- [test](./test.md) — JVM unit tests (`CounterTest`) via Gradle.
- [assemble](./assemble.md) — debug APK build.
- [screenshot](./screenshot.md) — device or harbor PNG receipt.
- [logcat](./logcat.md) — pid-filtered logcat receipt.
