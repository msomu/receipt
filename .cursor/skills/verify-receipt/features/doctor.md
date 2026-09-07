# Doctor

Before any receipt, an agent checks that Java, adb, and the sample Gradle wrapper are available. Doctor is read-only: no proof directory is created.

## Sub-features

- `doctor-java` — `java` on PATH and resolvable `JAVA_HOME` when needed.
- `doctor-adb` — `adb` on PATH; lists devices without acquiring a lease.
- `doctor-gradle` — `sample/gradlew` exists.
- `doctor-device-optional` — reports connected serial if any; absence is fine for `test` / `assemble`.

## How to get to it (user POV)

From the receipt repo root, run `./verify doctor`. No subcommand arguments. This is the first step in every `/receipt` playbook and before driving the sample app on a device.

## Driving it with verify

- **Dry run.** `./verify --dry-run doctor` — expect `ok: true` in JSON, no files written.
- **Health check.** `./verify doctor` — expect `"ok": true`, non-null `checks.java`, `checks.adb`, `checks.gradlew` ending in `sample/gradlew`.
- **Observable.** Exit code 0 when healthy; JSON `device` is serial or `null`.

## Gotchas

- Missing `ANDROID_HOME` does not fail doctor, but `test` / `assemble` will fail until `sdk.dir` is set in `sample/local.properties`.
- Doctor requires adb even when you only plan to run `test` (no device). Install platform-tools or set PATH.
- A stale `adb` daemon on `:5037` from another project can make `devices` look empty; only kill the daemon you started.
- `ok: false` with adb present but no device is still healthy for Gradle-only receipts.
