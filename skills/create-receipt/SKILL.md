---
name: create-receipt
description: "Interview a repo and write a project-local verify-<app> skill with a real CLI and a Feature Map. Use for /create-receipt when Android, KMP, or a Kotlin app has no scripted way to prove a change."
disable-model-invocation: true
---

# Create a receipt skill

You are writing for the next agent, who will open this repo cold. Markdown that says "run the tests" is not a skill. A CLI that prints JSON is.

## 1. Interview the repo

Answer from the tree. Ask the human only what the tree cannot tell you.

- **Surfaces.** Android APK, KMP shared, iOS, web (bun), CLI. Pick the primary. Note the rest.
- **Build.** Wrapper path, product flavor, the exact assemble and test task names. For Android read `applicationId`, launch activity, `compileSdk`. For KMP list targets. For web, the bun script that starts a real page.
- **Drive.** Existing harness first (Compose test tags, `adb`, Maestro, bun test, Playwright). Then the cheapest thing that a user would recognize: `adb -s` + screenshot, or a page URL.
- **Device.** Serial policy. If `adbharbor` exists, the generated CLI takes a lease and never `--force`. Pin `-s` always. If doctor has no device and `adbharbor` is on PATH, `screenshot` / `logcat` call `adbharbor submit` (harbor picks the serial; never pass `-s`). Cloud: no device + harbor MCP → `wait_for_run` + `get_proof`. Do not write a harbor URL into the skill.
- **Isolate.** Can two emulators or two web ports run at once? If not, say so.

If the checkout does not build, fix that (or report the exact missing file, often `local.properties`) before generating.

## 2. Write the CLI first

Copy [references/verify.template.py](references/verify.template.py) to `.cursor/skills/verify-<app>/verify`, mark it executable, and fill in the `CONFIG` block. Required surface:

```
verify --help
verify --dry-run <cmd>
verify doctor | test | assemble | screenshot | logcat
```

`screenshot` is capture-only. `--install` and `--launch` are opt-in. `ok` requires every step exit 0 and PNG magic. `logcat` follows adb exit and filters to the package pid.

When doctor has no device and `adbharbor` is on PATH, `screenshot` / `logcat` run `adbharbor submit --apk <apk> --package <id> --activity <activity>` and copy the PNG / logcat from `~/.adbharbor/runs/<id>/`. Harbor picks the serial. The CLI does not speak MCP.

Stdout is one JSON object. `--dry-run` lists the commands and writes nothing. Proof files go under `.receipt/proof/<run-id>/` and are never deleted by cleanup.

## 3. Write the skill

`.cursor/skills/verify-<app>/SKILL.md` with frontmatter `name: verify-<app>` and a description that names the app and surface. Sections, all filled from this repo:

- **Launch** — exact start command and ready signal. Teardown of what you started.
- **Doctor** — `verify doctor`. What "healthy" means.
- **Drive** — real test tags, activities, URLs. No placeholders.
- **Evidence** — where proof lands. The user path, not a test-only setter. Cloud with harbor MCP: `get_proof` PNG is the receipt.
- **Cleanup** — release the AdbHarbor lease you took; kill the emulator or server you started. Do not delete `.receipt/proof/`.
- **Helpers** — show the `verify` invocations.

## 4. Seed the Feature Map

`.cursor/skills/verify-<app>/features/README.md` plus 3–5 feature files. Shape is in [references/feature-map.md](references/feature-map.md). Each file answers: what it does, how a user opens it, how `verify` drives it, what usually lies.

## 5. Prove it once

Run `doctor`, then drive **one** mapped feature, then check the proof directory still exists after cleanup. A skill you never executed is a draft.

Point at `/maintain-receipt` for the upkeep loop.
