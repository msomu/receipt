# receipt

An agent does not get to say done until it leaves a receipt: Gradle output, a device or emulator screenshot, logcat.

Cursor plugin for Android and Kotlin Multiplatform. Tools first. Markdown second.

Inspired by [pstack](https://github.com/cursor/plugins/tree/main/pstack) (Lauren Tan).

## Install

1. Clone this repo.
2. Cursor → **Settings → Plugins → Add from GitHub** → `msomu/receipt`.
   Or copy the repo into `~/.cursor/plugins/local/receipt` and reload the window.
3. You should see `/receipt`, `/create-receipt`, `/maintain-receipt`.

## Talk script (10 minutes)

Kotlin Connect slot is 50 minutes. This is the 15-minute live proof, timed to 10 if questions run long.

1. **0:00 — the line.** Open this README. Read the first sentence out loud.
2. **1:00 — the sample.** `cd sample && ./verify doctor` — JSON, not a paragraph.
3. **3:00 — tests.** `./verify test` — `:app:testDebugUnitTest`, XML + log under `.receipt/proof/`.
4. **5:00 — the device.** `./verify assemble && ./verify screenshot --install --launch` for a cold start, then increment, then `./verify screenshot` (capture-only). Proof still there after the lease drops.
5. **8:00 — port it.** `/create-receipt` on any Android or KMP repo. It writes `.cursor/skills/verify-<app>/` with a CLI and a Feature Map. Tomorrow's homework is `/maintain-receipt`.
6. **10:00 — stop.** Clone it, run `verify`, watch the agent prove the change. Questions.

## Sample

Compose Android app, three tabs (Counter, Settings, About). AGP 9. JVM unit tests stand in for a shared module — AGP 9 does not host KMP in this sample. The `kmp-test` playbook is for repos that do.

```bash
cd sample
./verify --help
./verify --dry-run test
./verify doctor
./verify test
./verify assemble
./verify screenshot --install --launch   # cold start
./verify screenshot                      # current frame only
./verify logcat                          # filtered to the app pid
```

If `adbharbor` is on `PATH`, `screenshot` and `logcat` take a lease and never `--force`. Proof files live in `sample/.receipt/proof/` and survive cleanup.

## Use it on another repo

In that repo, run `/create-receipt`. The skill interviews the tree, writes a `verify` CLI (`--help`, `--dry-run`, JSON, `doctor` / `test` / `assemble` / `screenshot` / `logcat`), and seeds `features/` with 3–5 files. Then run `/receipt` so bug / feature / kmp-test work cannot finish without a receipt.

## License

MIT. Author: Somasundaram Mahesh.
