# receipt

An agent does not get to say done until it leaves a receipt: Gradle output, a device or emulator screenshot, logcat.

Cursor plugin for Android and Kotlin Multiplatform. Tools first. Markdown second.

Inspired by [pstack](https://github.com/cursor/plugins/tree/main/pstack) (Lauren Tan).

## Install

1. Clone this repo.
2. Cursor → **Settings → Plugins → Add from GitHub** → `msomu/receipt`.
   Or copy the repo into `~/.cursor/plugins/local/receipt` and reload the window.
3. You should see `/receipt`, `/create-receipt`, `/maintain-receipt`.

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

If `adbharbor` is on `PATH`, `screenshot` and `logcat` take a lease and never `--force`. No device + `adbharbor` on `PATH` → `adbharbor submit` (harbor picks the serial). Proof files live in `sample/.receipt/proof/` and survive cleanup.

## Local vs cloud

**Local Cursor** — phone on this Mac. Shell only. No MCP.

```
adbharbor acquire --any
./verify screenshot
```

Harbor still owns `:5037`. Do not talk to the phone around it.

**Cloud** — the VM has no USB. On the desk Mac: `adbharbor expose --via cloudflare` (or `ngrok` / `tailscale`). Paste **your** public URL + bearer token into Cursor Dashboard. This plugin ships neither a URL nor an MCP server.

No device + harbor MCP connected → submit the APK, then `wait_for_run` + `get_proof`. That PNG is the receipt. No fake screenshots.

## Use it on another repo

In that repo, run `/create-receipt`. The skill interviews the tree, writes a `verify` CLI (`--help`, `--dry-run`, JSON, `doctor` / `test` / `assemble` / `screenshot` / `logcat`), and seeds `features/` with 3–5 files. Then run `/receipt` so bug / feature / kmp-test work cannot finish without a receipt.

## License

MIT. Author: Somasundaram Mahesh.
