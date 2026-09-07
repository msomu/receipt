# Screenshot

Captures a PNG of the current device frame. Opt-in `--install` and `--launch` for cold start. Default is capture-only (whatever is on screen now).

## Sub-features

- `screenshot-capture` — `adb exec-out screencap -p` → `screen.png`.
- `screenshot-install` — `adb install -r` the debug APK (`--install`).
- `screenshot-launch` — `am start` MainActivity (`--launch`).
- `screenshot-harbor` — when no local device and `adbharbor` on PATH, `adbharbor submit` copies PNG from `~/.adbharbor/runs/<id>/`.

## How to get to it (user POV)

After a UI change or tab drive, an agent leaves a visual receipt. Cold start: `./verify screenshot --install --launch`. After manual taps: `./verify screenshot` only.

## Driving it with verify

Preconditions: debug APK built (`./verify assemble`); device connected, harbor lease, or harbor submit path.

- **Cold start proof.** `./verify screenshot --install --launch` — expect `screen.png` with PNG magic; Counter tab showing value `0`.
- **Capture after drive.** Tap controls via adb, then `./verify screenshot` (no `--launch` — relaunch resets counter).
- **No device + harbor.** `./verify screenshot` triggers harbor submit when `adbharbor` is on PATH.
- **Observable.** JSON `"ok": true` and `file sample/.receipt/proof/<utc>/screen.png` starts with `\x89PNG`.

## Gotchas

- `--launch` resets app state; a screenshot of `0` after increment means you relaunched, not that increment failed.
- Capture-only on a blank emulator home screen is a valid PNG but not proof of the sample app.
- Never `adbharbor release --force`; release only leases this run acquired.
- Two agents cannot share one serial without harbor leasing; second acquire blocks or fails.
