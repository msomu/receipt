# Feature map shape

`features/README.md` is the index and the sweep order. One sibling file per feature.

Each feature file starts with an H1 and one paragraph of user-visible behavior. Then exactly four H2s, in this order:

1. **What it does** — short IDs, one line each.
2. **How a user opens it** — every entry point a person would use.
3. **How verify drives it** — preconditions, then labeled steps: user action, exact command, observable result.
4. **What usually lies** — traps that fake a green or waste a lease.

No implementation details. Name paths, test tags, `contentDescription`s, task names, and proof files.
