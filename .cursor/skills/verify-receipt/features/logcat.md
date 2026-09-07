# Logcat

Dumps recent log lines filtered to the Receipt Sample process pid. Used alongside screenshots when the UI is involved.

## Sub-features

- `logcat-pid` — `adb shell pidof -s com.example.receiptsample`.
- `logcat-dump` — `adb logcat -d -t <lines> --pid <pid>` (default 200 lines).
- `logcat-harbor` — harbor submit path copies `logcat.txt` from the run directory when no local device.

## How to get to it (user POV)

After driving the sample app on device, capture logs to explain crashes or lifecycle. From repo root: `./verify logcat` or `./verify logcat --lines 500`.

## Driving it with verify

Preconditions: app running on device (`screenshot --install --launch` or manual start); serial pinned or leased.

- **After launch.** `./verify screenshot --install --launch` then `./verify logcat` — expect `logcat.txt` in proof dir.
- **Package not running.** `./verify logcat` without a running app — expect `"ok": false` or pid step failure.
- **Observable.** Proof directory contains non-empty `logcat.txt` when package is in foreground.

## Gotchas

- Logcat without a running package is expected to fail — that is not a harness bug.
- Unfiltered `adb logcat` is not this command; output is pid-scoped only.
- Process death after `force-stop` yields empty pid; relaunch before logcat.
- Harbor submit logcat requires the remote run to finish; do not read partial files mid-run.
