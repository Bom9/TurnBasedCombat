# Branch: feature/action-system

## Overview
This branch handles the combat mechanics, damage calculations, and the strategy logic for enemy AI decisions.

## Classes & Interfaces
* **`Action` (Interface):** The base contract for any move taken during a turn.
* **`SpecialSkill` (Interface):** Extends Action to include 3-turn cooldown tracking.
* **`EnemyActionStrategy` (Interface):** The "brain" allowing enemies to pick their moves.
* **Concrete Player Actions:** `BasicAttack`, `Defend`, `ShieldBash`, `ArcaneBlast`.
* **Concrete Enemy Actions:** `EnemyBasicAttack`.
