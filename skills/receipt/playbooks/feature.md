# Playbook: feature

Build the user-visible change, map it, then leave a receipt that a cold agent can replay.

## 1. Map it first

Open `verify-<app>/features/`. If this feature is missing, add a file before you code: what it does, how a user opens it, how `verify` drives it, what usually lies. A feature with no map entry cannot be proven.

## 2. Build

Implement against the map. Prefer test tags and `contentDescription` over coordinates. Shared logic lives where the KMP/JVM tests can reach it.

## 3. Prove

1. `verify doctor`
2. `verify test` — full module task, not a single class.
3. `verify assemble`
4. Drive every entry point the new feature file lists. One happy path is not enough if the file lists cancel / empty / error.
5. `verify screenshot` and `verify logcat` after the drive. No device + harbor MCP → `get_proof` instead.

## Stop

Done = the feature file, the CLI still matching the app, and a proof directory that survives cleanup. If assemble or the drive fails, fix the app or the harness — do not edit the map to hide a broken path.
