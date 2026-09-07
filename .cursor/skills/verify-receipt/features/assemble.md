# Assemble

Builds the debug APK for Receipt Sample (`:app:assembleDebug`). Required before `screenshot --install` or harbor submit.

## Sub-features

- `assemble-gradle` — `sample/gradlew :app:assembleDebug --rerun-tasks`.
- `assemble-log` — `assemble_task.log` in the proof directory.
- `assemble-apk` — output at `sample/app/build/outputs/apk/debug/app-debug.apk`.

## How to get to it (user POV)

Before installing on a device or submitting to AdbHarbor, build the APK. From repo root: `./verify assemble`.

## Driving it with verify

Preconditions: `./verify doctor` with `ok: true`; Android SDK configured.

- **Dry run.** `./verify --dry-run assemble` — lists gradlew command only.
- **Build.** `./verify assemble` — expect `"ok": true`, APK exists at `sample/app/build/outputs/apk/debug/app-debug.apk`.
- **Observable.** `assemble_task.log` ends with `BUILD SUCCESSFUL`; APK file size > 0.

## Gotchas

- `screenshot --install` without a prior assemble fails with `apk missing`.
- Debug and release APKs differ; this harness only builds debug.
- Parallel assembles on the same checkout share one Gradle daemon — not two isolated builds.
- AGP 9 requires JDK 17+; wrong Java version surfaces in `assemble_task.log`, not in verify JSON alone.
