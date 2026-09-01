# Tabs

Three destinations share one activity. The bottom bar is the only user entry.

## What it does

- `tab-counter` selects Counter.
- `tab-settings` selects Settings.
- `tab-about` selects About.
- Counter state survives a tab switch in the same process.

## How a user opens it

- Launch the app (Counter).
- Tap any labeled tab.

## How verify drives it

Preconditions: app in foreground.

- **Default.** Fresh launch shows `counter-value` `0`.
- **Round trip.** Increment to `1`, open About, return to Counter. Value is still `1`.
- **Proof.** Screenshot Counter after the round trip.

## What usually lies

- Process death resets the count. A cold start after `am force-stop` is not a failed persist.
