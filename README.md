# Branch: feature/engine-and-ui

## Overview
This branch orchestrates the main game loop, manages enemy spawn waves, and handles all Command Line Interface (CLI) inputs/outputs.

## Classes & Interfaces
* **`BattleEngine`:** The core controller that manages rounds, active combatants, and victory/defeat states.
* **`UserInterface`:** Handles the CLI display, `Scanner` inputs, and separates visual text from core logic.
* **`LevelManager`:** Stores the Easy/Medium/Hard configurations and handles the backup spawning logic.
