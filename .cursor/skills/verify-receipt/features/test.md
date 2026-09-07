# Test

Runs `:app:testDebugUnitTest` on the Receipt Sample module. JVM unit tests (`CounterTest`) prove counter increment/decrement/reset logic without a device.

## Sub-features

- `test-gradle` — invokes `sample/gradlew :app:testDebugUnitTest --rerun-tasks`.
- `test-log` — writes combined stdout/stderr to `test_task.log` in the proof directory.
- `test-results` — copies `sample/app/build/test-results/` into proof when present.

## How to get to it (user POV)

An agent proving a code change runs unit tests before claiming done. From repo root: `./verify test`. No device required. Maps to the Counter domain rules exercised in `sample/app/src/test/.../CounterTest.kt`.

## Driving it with verify

Preconditions: `./verify doctor` with `ok: true`; Android SDK available (`ANDROID_HOME` or `sample/local.properties`).

- **Dry run.** `./verify --dry-run test` — JSON lists gradlew command; no proof files.
- **Run tests.** `./verify test` — expect `"ok": true`, `"proof": "sample/.receipt/proof/<utc>/"`, `test_task.log` contains `BUILD SUCCESSFUL`.
- **Observable.** Exit code 0; proof directory contains `test_task.log` and optionally `test-results/`.

## Gotchas

- `SDK location not found` means `sdk.dir` / `ANDROID_HOME` is missing — not a test failure.
- Scoped test runs (`--tests CounterTest.increment`) are not this harness; `verify test` always runs the full `:app:testDebugUnitTest` task.
- A green chat message without `test_task.log` on disk is not a receipt.
- First run downloads Gradle and dependencies; allow several minutes.
