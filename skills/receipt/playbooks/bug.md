# Playbook: bug

Reproduce on the real surface, then fix, then leave a receipt that shows the before and the after.

## 1. Reproduce

1. Read the project-local `verify-<app>` skill. Run its `doctor`.
2. Drive the failing path from the feature map, not from a guess.
3. Capture a receipt of the failure: screenshot and/or logcat, plus the command JSON. If doctor cannot start the app, that is the bug.
4. Do not start editing until the failure is on disk.

## 2. Fix

Change the smallest surface that makes the reproduced path pass. No opportunistic refactors.

## 3. Prove

1. Re-run the same drive. The new screenshot or test XML must differ from the failure receipt.
2. Run the module's full test task (`:app:testDebugUnitTest`, `:shared:allTests`, or the verify CLI `test`).
3. If the bug was UI, `assemble` and `screenshot` on a pinned device.
4. Write both receipts under `.receipt/proof/<id>/` — `before/` and `after/`.

## Stop

You are done when `after/` exists and doctor still passes. You are blocked when the failure cannot be reproduced or the device lease is busy (exit 75) — wait or switch devices, do not `--force`.
