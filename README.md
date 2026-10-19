# Catch the Egg

A simple Java Swing game where the player controls a basket and catches falling eggs while avoiding chicken droppings.

The project is organized so that game objects, gameplay logic, and the graphical interface are separated into different packages.

---

## Project Structure

```text
catch_the_egg/
├── resources/
│   └── Game images
│
├── src/
│   ├── entities/
│   ├── exceptions/
│   ├── game/
│   └── ui/
│
├── out/
│   └── Compiled classes
│
└── CatchTheEgg.jar
```

### `entities/`

Contains the objects that exist inside the game, such as the basket, chickens, eggs, and falling objects.

### `game/`

Contains the actual gameplay logic. This is where spawning, object movement, collision detection, scoring, difficulty, game-over conditions, and game state are handled.

### `ui/`

Contains everything related to the Swing interface: the game window, start screen, help screen, game rendering, and player input.

### `exceptions/`

Contains custom exceptions used by the game.

---

# Source Code Guide

## `src/entities/`

The `entities` package contains the objects used by the game. These classes mostly describe what an object is and store its properties. The overall rules for what happens to those objects are handled primarily by `Game.java`.

---

### `Basket.java`

Represents the player's basket.

It stores:

- X and Y position
- Width and height
- Movement amount

It also provides methods for moving the basket left and right and changing/reading its X position.

**Look here when:**

- Changing basket size
- Changing basket movement distance
- Changing basket position data
- Adding properties to the basket

If the basket does not respond correctly to keyboard or mouse input, also check `GamePanel.java` and `Game.java`.

---

### `FallingObject.java`

Abstract base class for objects that fall down the screen.

It stores:

- X position
- Y position
- Falling speed

Its `fall()` method moves the object downward.

Both eggs and chicken droppings inherit from this class.

**Look here when:**

- Changing how falling movement works
- Changing properties shared by all falling objects
- Changing the common falling speed behaviour

For rules about catching or missing an object, look in `Game.java` instead.

---

### `Egg.java`

Abstract base class for all eggs.

It extends `FallingObject` and adds the egg's point value.

**Look here when:**

- Changing something that applies to every type of egg
- Adding a property shared by all eggs
- Changing how an egg stores its points

For individual egg types, see `EggTypes.java`.

---

### `EggTypes.java`

Contains the three concrete egg types:

- `WhiteEgg`
- `NaturalEgg`
- `GoldenEgg`

Each type extends `Egg` and defines its own point value.

**Look here when:**

- Changing the points awarded by a particular egg
- Adding another type of egg
- Changing the properties of a specific egg type

For the probability of which egg appears, see `EggFactory.java`.

---

### `EggFactory.java`

Responsible for creating a random egg.

It decides which egg type is created when the game requests a new random egg.

**Look here when:**

- Changing egg spawn probabilities
- Changing how random egg types are selected
- Adding another random egg type to the factory

If the point value itself is wrong, check `EggTypes.java`.

---

### `Chicken.java`

Represents a chicken and stores its position.

**Look here when:**

- Changing chicken position data
- Adding properties to chickens
- Changing how a chicken's basic data is represented

The actual spawning behaviour involving chickens is handled by `Spawner.java`.

---

### `ChickenShit.java`

Represents a falling chicken dropping.

It extends `FallingObject` and stores the penalty associated with it.

**Look here when:**

- Changing the penalty value stored by a dropping
- Adding properties specific to chicken droppings

The actual effect of catching or missing it is handled by `Game.java`.

---

### `BrokenEgg.java`

Stores information about a broken egg effect.

It contains:

- Position
- Points associated with the egg
- Creation time

The class itself mainly stores the information needed for the effect. The gameplay code determines when the effect is created and removed.

**Look here when:**

- Changing information stored for broken eggs
- Adding properties to the broken-egg effect

For when and where broken eggs are created, check `Game.java`.

---

### `SplashedShit.java`

Stores the position of a chicken-dropping splash effect.

**Look here when:**

- Changing data associated with the splash effect
- Adding properties to the splash effect

For when the splash is created or removed, check `Game.java`.

---

# `src/game/`

This package contains the main gameplay logic.

If the game is **doing something incorrectly**, rather than simply **drawing something incorrectly**, this is usually the first package to inspect.

---

### `Game.java`

This is the main gameplay controller.

It coordinates most of the game's behaviour, including:

- Starting and resetting the game
- Spawning eggs and chicken droppings
- Updating falling objects
- Detecting objects reaching the bottom
- Detecting caught objects
- Applying the result of catching an object
- Updating the score
- Updating missed eggs
- Checking game-over conditions
- Increasing difficulty
- Moving the basket
- Managing broken-egg and splash effects
- Validating the game state

It also provides access to the main game objects and state.

**This is the first file to check for most gameplay bugs.**

For example:

- Eggs aren't being caught correctly → `Game.java`
- Eggs aren't being counted as missed → `Game.java`
- Chicken droppings don't apply their penalty → `Game.java`
- Difficulty doesn't increase correctly → `Game.java`
- Game doesn't end correctly → `Game.java`
- Basket movement logic is wrong → `Game.java`
- Game reset behaves incorrectly → `Game.java`

If the problem is specifically about an object's own properties, also inspect the corresponding class in `entities/`.

---

### `GameState.java`

Stores the changing numerical state of the game.

It manages:

- Current score
- Number of missed eggs
- Maximum allowed missed eggs

It provides methods for adding/subtracting points, recording missed eggs, reading the values, and resetting the state.

**Look here when:**

- Score values are wrong
- Missed-egg count is wrong
- Score isn't resetting
- Missed eggs aren't resetting
- The maximum missed-egg value needs to change

`Game.java` decides **when** these values should change; `GameState.java` stores and modifies the values themselves.

---

### `Spawner.java`

Controls the creation of falling objects.

It:

- Stores the chickens used for spawning
- Stores the current spawn speed
- Selects a random chicken
- Creates eggs
- Creates chicken droppings
- Tracks whether an egg is currently falling

**Look here when:**

- Objects aren't spawning
- Objects spawn from the wrong chicken
- Spawn speed needs changing
- The game should allow/disallow another egg spawn
- Chicken selection needs changing

For deciding **which type of egg** gets created, check `EggFactory.java`.

---

# `src/ui/`

This package contains the graphical interface and player input.

If the game logic is correct but something is **displayed incorrectly, positioned incorrectly, or doesn't respond to input**, start here.

---

### `Main.java`

The entry point of the application.

It contains the `main()` method that starts the game.

**Look here when:**

- The application does not launch
- The program entry point needs changing

Most window and screen problems belong in `GameWindow.java` instead.

---

### `GameWindow.java`

Creates and manages the main application window.

It handles:

- Window title
- Window size
- Resizing
- The Swing `CardLayout`
- Creating the Start, Game, and Help panels
- Switching between screens

The three main screens are:

```text
START
GAME
HELP
```

It provides methods for switching between them:

- `startGame()`
- `showHelpScreen()`
- `showStartScreen()`

**Look here when:**

- Window size needs changing
- Window title needs changing
- Screen switching doesn't work
- A panel isn't being added to the application
- Start/Game/Help navigation is broken

---

### `StartPanel.java`

Controls the start screen.

It creates and displays the:

- START button
- HELP button
- EXIT button

It also draws the start-screen background and connects the buttons to `GameWindow`.

**Look here when:**

- Start-screen buttons need changing
- START button doesn't work
- HELP button doesn't work
- EXIT button doesn't work
- Start-screen appearance needs changing
- Start-screen layout needs changing

If the actual transition between screens is broken, check `GameWindow.java`.

---

### `HelpPanel.java`

Controls the Help screen.

It displays the game's instructions and provides the BACK button for returning to the start screen.

**Look here when:**

- Help text needs changing
- Help-screen layout needs changing
- Help-screen background needs changing
- BACK button needs changing

---

### `GamePanel.java`

This is the main graphical and input-handling part of the game.

It handles:

- Displaying the game
- Drawing the background
- Drawing the basket
- Drawing eggs
- Drawing chicken droppings
- Drawing broken eggs
- Drawing splash effects
- Displaying score/game information
- Keyboard input
- Mouse/touchpad input
- Game-panel layout
- Loading the chicken image
- Starting the gameplay process

It also contains `paintComponent()`, which is responsible for drawing the current game state.

**Look here when:**

- Something isn't being drawn
- An image is in the wrong position
- An image has the wrong size
- The game background needs changing
- Score text needs changing
- Keyboard controls aren't working
- Mouse/touchpad controls aren't working
- Gameplay visual scaling/resizing is wrong
- The game panel layout needs changing

If the object is visually drawn correctly but its **actual gameplay position or collision behaviour** is wrong, check `Game.java` instead.

---

# How the Main Parts Connect

Understanding this flow makes it much easier to find bugs.

## Starting the application

```text
Main.java
    ↓
GameWindow.java
    ↓
StartPanel.java
```

`Main.java` launches the application.

`GameWindow.java` creates the main window and the different panels.

`StartPanel.java` is initially displayed.

---

## Starting a game

```text
StartPanel
    ↓
GameWindow.startGame()
    ↓
GamePanel.startGame()
    ↓
Game
```

The START button in `StartPanel.java` asks `GameWindow` to start the game.

`GameWindow.java` switches the visible panel to the game panel.

`GamePanel.java` starts the gameplay process.

---

## Falling eggs

```text
Game
 ↓
Spawner
 ↓
EggFactory
 ↓
EggTypes
 ↓
FallingObject
 ↓
GamePanel
```

`Game.java` controls when an egg should spawn.

`Spawner.java` chooses a chicken and creates the egg.

`EggFactory.java` decides which type of egg to create.

`EggTypes.java` provides the specific egg class.

The egg inherits its falling behaviour from `FallingObject.java`.

Finally, `GamePanel.java` draws the egg on the screen.

---

## Catching an egg

```text
GamePanel
    ↓
Game
    ↓
GameState
```

`GamePanel.java` displays the basket and receives player input.

`Game.java` controls the basket position and determines whether a falling object has been caught.

If an egg is caught, `Game.java` applies the appropriate result and updates `GameState.java`.

`GameState.java` stores the resulting score.

---

## Game difficulty

Difficulty is controlled primarily through:

```text
Game.java
   ↓
Spawner.java
   ↓
FallingObject / spawned objects
```

`Game.java` decides when difficulty should increase.

`Spawner.java` stores the relevant spawning speed.

The falling objects receive their speed when they are created.

If difficulty progression is wrong, start with `Game.updateDifficulty()` in `Game.java`.

---

# Quick Troubleshooting Map

### "The window is wrong."

Check:

```text
GameWindow.java
```

### "The Start screen is wrong."

Check:

```text
StartPanel.java
```

### "The Help screen is wrong."

Check:

```text
HelpPanel.java
```

### "The game looks wrong."

Check:

```text
GamePanel.java
```

### "Keyboard/mouse controls aren't working."

Start with:

```text
GamePanel.java
```

Then check basket movement in:

```text
Game.java
Basket.java
```

### "The basket is moving incorrectly."

Check:

```text
Basket.java
Game.java
GamePanel.java
```

### "Eggs aren't spawning."

Check:

```text
Game.java
Spawner.java
```

### "The wrong egg type is spawning."

Check:

```text
EggFactory.java
EggTypes.java
```

### "Eggs are falling incorrectly."

Check:

```text
FallingObject.java
Spawner.java
Game.java
```

### "Egg catching is wrong."

Check:

```text
Game.java
```

Especially the object-catching logic.

### "Score is wrong."

Check:

```text
Game.java
GameState.java
EggTypes.java
```

### "Missing eggs aren't counted correctly."

Check:

```text
Game.java
GameState.java
```

### "Chicken droppings aren't behaving correctly."

Check:

```text
ChickenShit.java
Spawner.java
Game.java
```

### "Game over isn't triggering correctly."

Check:

```text
Game.java
GameState.java
```

### "The game doesn't reset correctly."

Check:

```text
Game.java
GamePanel.java
```

### "An egg-breaking or dropping-splash effect is wrong."

Check:

```text
BrokenEgg.java
SplashedShit.java
Game.java
GamePanel.java
```

---

# Compilation and Running

From the project root:

```bash
javac -d out src/entities/*.java src/game/*.java src/exceptions/*.java src/ui/*.java
```

Copy the resources into the compiled output:

```bash
cp -r resources out/
```

Run the game:

```bash
java -cp out ui.Main
```

## Creating the runnable JAR

From the project root:

```bash
jar cfe CatchTheEgg.jar ui.Main -C out .
```

Run it with:

```bash
java -jar CatchTheEgg.jar
```

The JAR should contain the compiled classes as well as the resources required by the game.

---

# Development Philosophy

The project deliberately keeps responsibilities separated:

```text
entities/   → What objects exist
game/       → What happens during gameplay
ui/         → What the player sees and controls
exceptions/ → Game-specific errors
```

When changing something, try to start in the package that owns that responsibility instead of putting unrelated logic into another class.

For example:

- **Change an egg's properties** → `entities/`
- **Change when an egg appears** → `game/`
- **Change how an egg is drawn** → `ui/GamePanel.java`
- **Change what happens when an egg is caught** → `game/Game.java`
- **Change the score storage** → `game/GameState.java`
- **Change the start screen** → `ui/StartPanel.java`
- **Change screen navigation** → `ui/GameWindow.java`

This makes the project easier to debug, extend, and explain without having to search through the entire codebase.
