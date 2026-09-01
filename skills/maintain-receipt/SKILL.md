---
name: maintain-receipt
description: "Refresh a project-local verify-<app> skill and Feature Map against the current app. Use for /maintain-receipt when routes, test tags, Gradle tasks, or screens have drifted."
disable-model-invocation: true
---

# Maintain a receipt skill

The map rots the first time someone renames a screen. This is the upkeep loop for a skill written by `/create-receipt`.

## Outcomes

Say which one you reached:

- **clean** — every feature was read in source and driven live. Nothing to ship.
- **changed** — one PR of proven harness or map fixes.
- **blocked** — could not finish coverage, or a product bug is real. Name the blocker.

## Scope

Edit only the verify skill directory (`SKILL.md`, `verify`, `features/`). Never change product code on this pass. A map line that the app no longer does is either drift (fix the map) or a regression (report it, leave the map describing the intended user path).

## Pass

1. **Find it.** `.cursor/skills/verify-*/`. Several → ask which. None → `/create-receipt`.
2. **Index.** README entries match sibling files. Drop dead links.
3. **Source.** For each feature file, read the current UI and Gradle. Cite the path that drifted.
4. **CLI.** `--help` still lists `doctor test assemble screenshot logcat`. Dry-run the assemble and test tasks and confirm the names exist in the wrapper (`./gradlew tasks`).
5. **Live.** `doctor`, then drive every feature once. Doctor after any failed drive. Release leases you acquired. Confirm `.receipt/proof/` still has the files after teardown.
6. **Triage.** Wrong user path → fix the map. Harness cannot drive a working path → fix `verify`. App is broken → report, do not paper it over.

Ship one PR of proven corrections, or report clean/blocked with the coverage list. Do not commit scratch notes.
