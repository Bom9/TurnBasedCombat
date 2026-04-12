# Turn-Based Combat Arena

A command-line tactical battle game built in Java for SC2002 (Object-Oriented Design & Programming) at NTU.

You pick a character, grab two items, choose your difficulty, and fight through waves of enemies one turn at a time. Simple concept — but the code underneath is designed with clean OO principles so it's easy to extend and maintain.

---

## How to Play

**1. Pick your fighter**

| Class | HP | ATTACKK | DEFEND | SPEEDD | Special Skill |
|---|---|---------|--------|--------|---|
| Warrior | 260 | 40      | 20     | 30     | Shield Bash — deals damage and stuns the target for 2 turns |
| Wizard | 200 | 50      | 10     | 20     | Arcane Blast — hits all enemies; each kill permanently adds +10 ATK |

**2. Pack your items** (pick any 2, duplicates allowed)

| Item | Effect |
|---|---|
| Potion | Heals 100 HP (capped at max) |
| Power Stone | Fires your special skill for free — no cooldown affected |
| Smoke Bomb | Enemy attacks deal 0 damage for 2 turns |

**3. Choose your difficulty**

| Level | Initial Enemies | Backup Wave |
|---|---|---|
| Easy | 3 Goblins | — |
| Medium | 1 Goblin + 1 Wolf | 2 Wolves |
| Hard | 2 Goblins | 1 Goblin + 2 Wolves |

Backup enemies only spawn once the first wave is fully wiped out — so don't celebrate too early.

**4. Fight**

Each round, you pick your action *before* anyone moves. Faster combatants act first (based on SPEED), but your choice is locked in ahead of time — so Defend and Smoke Bomb actually protect you even if enemies are quicker.

Actions available each turn:
- **Basic Attack** — `Damage = max(0, ATTACK − target DEFEND)`
- **Defend** — +10 DEFEND for this turn and the next
- **Use Item** — consume one item from your inventory
- **Special Skill** — class ability, 3-turn cooldown

---

## Running the Project

Requires Java 17+.

```bash
# Compile
javac -d out -sourcepath src $(find src -name "*.java")

# Run
java -cp out src.Main
```

Or just open it in IntelliJ — the `.iml` file is already in the repo.

---

## Project Structure

```
src/
├── character/       # Combatant, Player, Enemy, Warrior, Wizard, Goblin, Wolf
├── action/          # Action interface, BasicAttack, Defend, ShieldBash, ArcaneBlast
├── effects/         # StatusEffect, Stun, DefenseBuff, SmokeBombEffect
├── items/           # Item, Potion, PowerStone, SmokeBomb
├── strategy/        # EnemyStrategy interface, BasicEnemyStrategy
├── engine/          # BattleEngine, GameSetup, TurnOrderStrategy, SpeedBasedTurnOrder
├── level/           # DifficultyLevel (enum), SpawnConfig, LevelManager
├── ui/              # BattleDisplayUI, GameInputUI, ConsoleBattleDisplay, ConsoleGameInput
└── Main.java
```

---

## Design Notes

This project was a good excuse to actually apply SOLID principles rather than just read about them. A few decisions worth highlighting:

- **BattleEngine never touches the CLI directly.** It talks to `BattleDisplayUI` and `GameInputUI` interfaces. Swapping the terminal out for a GUI someday means writing two new classes, not touching the game logic.
- **New actions and status effects plug in without engine changes.** `BattleEngine` calls `execute()` on any `Action` and queries `StatusEffect` through its hook methods — no `instanceof` chains anywhere in the engine.
- **Enemy AI is swappable.** Every enemy holds an `EnemyStrategy`. Right now they all use `BasicEnemyStrategy` (always attack), but plugging in smarter behaviour is just a matter of implementing the interface.
- **Turn order is an abstraction.** `SpeedBasedTurnOrder` is the only implementation today, but `BattleEngine` works with the `TurnOrderStrategy` interface, so changing the ordering rule later won't break anything.

---

## Contributors

| GitHub          | Role                   |
|-----------------|------------------------|
| Bom9            | Engine and integration |
|SusanosHermanes  | UI                     |
| yzzzap          | Action and Characters  |
| Samrithi Satish | Items and effects      |