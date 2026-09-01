# Playbook: kmp-test

Prove shared Kotlin the same way you prove UI: a command, an XML result, a HEAD sha.

## 1. Find the real test task

From the repo, not from memory:

| Layout | Task to run |
|---|---|
| `kotlin("multiplatform")` shared module | `:<module>:allTests` or every target the module publishes (`iosSimulatorArm64Test`, `jvmTest`, `testDebugUnitTest`) |
| Android-only domain in `:app` | `:app:testDebugUnitTest` |
| JVM-only | `:module:test` |

`verify test` must invoke that task. A Gradle `--tests SomeClass` run is a probe, not a receipt.

## 2. Write the test that can go red

Add or change the test first. Run it. Confirm it fails for the reason you expect. Then write the production code.

## 3. Prove

1. Full module test task. Copy `build/test-results/**/*.xml` into `.receipt/proof/<id>/`.
2. Record `git rev-parse HEAD` in the same shell.
3. If the shared change is visible on Android, `assemble` + `screenshot` as well. A green `allTests` with a broken Activity is not done.

## Stop

Done = XML on disk at that HEAD. Blocked = the wrapper exits 0 while printing `BUILD FAILED` (read the build-result line; do not trust exit code alone) or a fresh worktree is missing `local.properties`.
