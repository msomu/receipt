# Settings

The Settings tab toggles dark mode for the whole app.

## What it does

- `dark-mode-label` reads `Dark mode off` or `Dark mode on`.
- `dark-mode` flips the theme.

## How a user opens it

- Tap `Settings tab` on the bottom bar.

## How verify drives it

Preconditions: app in foreground.

- **Open.** Tap `Settings tab`. Heading `Settings` is visible.
- **Toggle on.** Tap `Dark mode`. Label becomes `Dark mode on`.
- **Toggle off.** Tap `Dark mode` again. Label becomes `Dark mode off`.
- **Proof.** `./verify screenshot` on the Settings tab.

## What usually lies

- Dynamic color on the emulator can look like a failed toggle. The sample forces `dynamicColor = false`.
- The switch state is in-memory. Killing the activity loses it. That is expected.
