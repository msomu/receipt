# About

The About tab states the version and the receipt thesis.

## What it does

- `about-version` is `Receipt Sample 1.0`.
- `about-thesis` is `An agent does not get to say done until it leaves a receipt.`

## How a user opens it

- Tap `About tab` on the bottom bar.

## How verify drives it

Preconditions: app in foreground.

- **Open.** Tap `About tab`.
- **Read.** Dump the tree. `about-version` and `about-thesis` match the strings above.
- **Proof.** `./verify screenshot`.

## What usually lies

- Do not treat the README thesis as proof. The on-device string is the receipt.
