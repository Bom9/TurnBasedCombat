# Branch: feature/combatant-hierarchy

## Overview
This branch contains the foundational character entities and the logic for calculating turn order based on speed.

## Classes & Interfaces
* **`Combatant` (Abstract):** The base class holding shared stats (HP, ATK, DEF, SPD).
* **`Player` (Abstract):** Base class for user-controlled characters.
* **`Enemy` (Abstract):** Base class for AI-controlled characters.
* **Concrete Characters:** `Warrior`, `Wizard`, `Goblin`, `Wolf`.
* **Turn Order:** `TurnOrderStrategy` (Interface), `SpeedBasedTurnOrder` (Concrete).