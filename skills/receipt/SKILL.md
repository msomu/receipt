---
name: receipt
description: "Router for Android and KMP work. An agent does not get to say done until it leaves a receipt. Use for /receipt, bug fixes, features, or KMP/shared tests when the proof must be Gradle, a device screenshot, or logcat — not a paragraph."
disable-model-invocation: true
---

# receipt

Done means artifacts exist on disk. A sentence that says the tests passed is not a receipt.

## Route

Pick one playbook. Do not invent a fourth.

| Work | Playbook |
|---|---|
| Something is broken | [playbooks/bug.md](playbooks/bug.md) |
| Something new should exist | [playbooks/feature.md](playbooks/feature.md) |
| Shared / KMP / JVM domain tests | [playbooks/kmp-test.md](playbooks/kmp-test.md) |

If the repo has no project-local verify skill, stop and run `/create-receipt` first.

## Receipt

A receipt is a directory of files the next agent can open without you:

1. **Command output** — Gradle, bun, or the verify CLI JSON. Exit code in the file, not in chat.
2. **One user-visible proof** — device/emulator screenshot, or a web page drive, or the test XML that went red then green.
3. **Log when the UI is involved** — `logcat` (pinned `adb -s`) or the web server log.

Name the directory. Leave it. Cleanup of processes and leases must not delete it.

## Where the phone is

- **This machine** — `./verify` / `adbharbor` CLI. Harbor owns `:5037`. No MCP.
- **No USB + harbor MCP connected** — submit the APK (`adbharbor submit` or `POST /v1/runs`), then `wait_for_run` + `get_proof`. That PNG is the receipt.
- This plugin ships no harbor URL and no MCP server. The user pastes *their* URL + token in Cursor Dashboard.

## Hard rules

- Pin every adb call with `-s`. If `adbharbor` is on PATH, take a lease before any write (install, launch, tap, screenshot). Never `adbharbor release --force`.
- No device + `adbharbor` on PATH → `adbharbor submit`. Harbor picks the serial. Never pass `-s`.
- No device + harbor MCP → `wait_for_run` + `get_proof`. Do not fake a screenshot.
- Run the full test task for the module you touched. A scoped class run is not a receipt.
- Attribute a green run to the `git rev-parse HEAD` of the same shell.
- If you cannot produce a receipt, you are blocked. Say what command failed and stop.

## After the playbook

Hand the receipt path to the human. Then send the work to review. Do not claim "it works" from a build that you did not run.
