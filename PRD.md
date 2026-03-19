# Product Requirements Document (PRD): Turn-Based Combat Arena

## 1. Product Overview
The Turn-Based Combat Arena is a single-player tactical combat game that runs entirely within a Command Line Interface (CLI). Players select a character class, equip items, and battle through predefined waves of enemies. The combat system is turn-based, utilizing speed statistics to determine action order. The game concludes when either all enemies are eliminated (Victory) or the player's health drops to zero (Defeat), with no draw states permitted.

## 2. Goals & Success Criteria
* **System Integrity:** Deliver a fully functional, bug-free CLI game loop that accurately tracks combat math, turn queues, and status durations.
* **Architectural Excellence:** The underlying codebase must rigorously apply Object-Oriented Design (OOD) and SOLID principles. 
* **Decoupled Design:** Complete separation of the User Interface (CLI display/inputs) from the core battle logic engine.
* **Extensibility:** The architecture must natively support the future addition of new character classes, items, and AI behaviors without requiring modification of the core game engine.

---

## 3. User Experience & Game Flows

### 3.1 Initiation (Loading Screen)
Before combat begins, the system must prompt the user through a setup sequence:
* Display available Player classes (Warrior, Wizard) and their base attributes.
* Capture the user's Player class selection.
* Prompt the user to select two single-use items for their inventory (duplicates are permitted).
* Display the available difficulty levels (Easy, Medium, Hard) and the corresponding enemy combatant counts.
* Display the available Enemy types (Goblin, Wolf) and their base attributes.
* Capture the user's difficulty selection to initiate the game.

### 3.2 Core Gameplay Loop
The game operates in sequential rounds governed by a Turn Order Strategy. 
* **Turn Sequence:** Order is determined by the `Speed` attribute; the highest speed acts first. Each entity takes exactly one action per turn.
* **Round Execution Steps:**
  1. Trigger backup enemy spawns if the initial wave is completely defeated.
  2. Apply and resolve existing status effects (e.g., skip turns for stunned entities).
  3. Update HP based on damage or healing. HP cannot fall below 0; if damage exceeds current HP, it clamps to 0 and the entity is marked as defeated/eliminated.
  4. Check for game-ending conditions.
  5. Prompt the player to choose an action via text menu; Enemies automatically execute a BasicAttack.
  6. Execute the chosen action.
  7. Update HP, apply new status effects, and consume inventory items if used.
  8. End the turn, display the alive/eliminated status of all combatants, and perform a final check for game-ending conditions.

### 3.3 Game Over States
* **Victory Screen:** Triggered when all enemies are defeated. Displays a congratulatory message, remaining Player HP, and the total rounds played.
* **Defeat Screen:** Triggered when the Player's HP reaches zero. Displays a "try again" message, the number of enemies remaining, and the total rounds survived.
* **Post-Game Menu:** Provides options to replay the current configuration, start a new game (return to the initiation screen), or exit the application.

---

## 4. Functional Requirements

### 4.1 Combatants
Combatants share common properties but possess unique baseline stats and abilities.

| Type | Class/Entity | HP | ATK | DEF | SPD | Special Skill / Notes |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Player** | **Warrior** | 260 | 40 | 20 | 30 | **Shield Bash**: Deals BasicAttack damage; stuns target for current & next turn. |
| **Player** | **Wizard** | 200 | 50 | 10 | 20 | **Arcane Blast**: Deals BasicAttack damage to all active enemies. Kills add +10 ATK permanently for the level. |
| **Enemy** | **Goblin** | 55 | 35 | 15 | 25 | N/A (Uses BasicAttack) |
| **Enemy** | **Wolf** | 40 | 45 | 5 | 35 | N/A (Uses BasicAttack) |

### 4.2 Actions & Combat Math
Players can choose one of four actions per turn. Enemies default to Basic Attack.

* **Basic Attack:** Performs an attack on a selected target. 
  * *Formula:* `Damage = max(0, Attacker ATK - Target DEF)`. Minimum HP post-damage is 0.
* **Defend:** Temporarily increases the user's Defense by `10` for the current round and the following round.
* **Item:** Consumes a selected item from the inventory.
* **Special Skill:** Executes the class-specific ability. 
  * *Cooldown:* 3 turns (including the current round). Cooldowns only decrease if the combatant actively takes a turn.

### 4.3 Items (Single-Use)
* **Potion:** Heals the user. 
  * *Formula:* `New HP = min(Current HP + 100, Max HP)`.
* **Power Stone:** Triggers the player's Special Skill without initiating or altering the skill's cooldown timer (grants a free use).
* **Smoke Bomb:** Nullifies enemy damage (Enemy attacks deal `0` damage) for the current turn and the subsequent turn.

### 4.4 Difficulty Levels & Spawning Rules
Backup spawns trigger simultaneously *only* after all entities in the initial wave are defeated.

| Level | Difficulty | Initial Spawn | Backup Spawn |
| :---: | :--- | :--- | :--- |
| 1 | **Easy** | 3 Goblins | *(None)* |
| 2 | **Medium** | 1 Goblin, 1 Wolf | 2 Wolves |
| 3 | **Hard** | 2 Goblins | 1 Goblin, 2 Wolves |

---

## 5. Technical & Architectural Requirements

### 5.1 System Abstractions
* **Battle Management:** Must track rounds, manage turn queues, process actions, and evaluate win/loss states.
* **Status Effects Mechanism:** The system must be capable of applying effects, maintaining durations across multiple turns, and cleanly removing effects upon expiration.
* **AI Strategy:** While enemies currently only use BasicAttack, the system must utilize a strategy abstraction to allow for complex enemy decision-making in future iterations.
* **Turn Order Determination:** Must be abstracted to support alternative sorting algorithms beyond the current speed-based implementation.

### 5.2 SOLID Principles Mandate
The implementation must provide concrete evidence of the following:
* **Single Responsibility Principle (SRP):** Classes must have only one reason to change.
* **Open-Closed Principle (OCP):** New `Action` or `StatusEffect` classes must be integrable without altering the `BattleEngine`.
* **Liskov Substitution Principle (LSP):** `Player` and `Enemy` objects must be perfectly interchangeable under a broader `Combatant` abstraction.
* **Interface Segregation Principle (ISP):** Interfaces must be specific and not bloated with unused methods.
* **Dependency Inversion Principle (DIP):** High-level modules (e.g., `BattleEngine`) must depend on abstractions (interfaces/abstract classes), not concrete implementations.
