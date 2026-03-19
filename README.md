# Branch: feature/items-and-effects

## Overview
This branch builds the inventory system, single-use consumables, and lingering status effects that alter standard combat rules.

## Classes & Interfaces
* **`Item` (Interface):** The base contract for inventory consumables.
* **`StatusEffect` (Abstract):** The base class for buffs/debuffs that track turn duration.
* **Concrete Items:** `Potion` (Heal), `PowerStone` (Free Skill), `SmokeBomb` (Nullify Damage).
* **Item Action:** `UseItem` (The Action implementation that triggers an Item).
* **Concrete Effects:** `Stun`, `ArcaneBlastBuff`.
