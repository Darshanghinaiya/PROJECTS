# Memory Task Game (JavaFX)

A level-based memory game built using JavaFX where players observe and repeat a sequence of highlighted squares. The difficulty increases with each level, making it a simple yet effective brain-training application.

---

## Overview

The game displays a sequence on a 3×3 grid. The player must remember and reproduce the sequence correctly. Each successful attempt advances the player to the next level with a longer sequence.

---

## Features

### Gameplay
- Sequence display using highlighted squares (Yellow)
- User repeats the sequence by clicking squares
- Instant feedback:
  - Correct input → Green flash
  - Wrong input → Red flash

### Level System
- Starts from Level 1
- Sequence length increases with each level
- Difficulty increases progressively

### Error Handling
- Shows alert on incorrect sequence
- Restarts the level after mistake
- Disables input during sequence playback

### Progression
- Displays success message after completing level
- Automatically moves to next level

---

## UI Details

- Built using JavaFX GridPane
- 3×3 interactive grid layout
- Smooth animations using PauseTransition
- Color-based feedback system:
  - Yellow → Sequence display
  - Green → Correct input
  - Red → Incorrect input

---

## Technologies Used

- Java
- JavaFX
- Event-driven programming
- JavaFX animations (PauseTransition)

---

## Key Concepts

- UI handling in JavaFX
- Animation without blocking UI thread
- Game state management
- Random sequence generation
- User input validation

---

## How to Run

1. Install Java (JDK 8 or higher)
2. Ensure JavaFX is configured (for JDK 11+)
3. Compile and run:

```bash
javac MemoryTaskGame.java
java MemoryTaskGame
