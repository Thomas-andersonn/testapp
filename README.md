# Snake Game for Android

A classic Snake game built with Jetpack Compose for Android. The game features smooth animations, touch controls, and a modern Material Design 3 interface.

## Features

- **Classic Snake Gameplay**: Control a snake to eat food and grow longer
- **Touch Controls**: Intuitive directional buttons for easy gameplay
- **Score Tracking**: Real-time score display
- **Pause/Resume**: Pause the game at any time
- **Game Over Handling**: Clear game over screen with final score
- **Modern UI**: Beautiful Material Design 3 interface with green theme
- **Responsive Design**: Works on different screen sizes and orientations

## Game Controls

- **Directional Buttons**: Use the arrow buttons to control snake direction
- **Pause/Play Button**: Pause or resume the game
- **Restart Button**: Start a new game

## Game Rules

1. Control the snake using the directional buttons
2. Eat the red food to grow longer and increase your score
3. Avoid hitting the walls or your own tail
4. Try to achieve the highest score possible!

## Technical Details

- **Framework**: Jetpack Compose
- **Language**: Kotlin
- **Minimum SDK**: API 34 (Android 14)
- **Target SDK**: API 35 (Android 15)
- **Architecture**: MVVM with Compose State management

## Project Structure

```
snake/
├── app/
│   └── src/main/java/com/example/snake/
│       ├── MainActivity.kt          # Main activity and game composable
│       ├── GameState.kt             # Data classes for game state
│       ├── GameEngine.kt            # Game logic and rules
│       ├── GameBoard.kt             # Game board rendering
│       ├── GameControls.kt          # Touch controls UI
│       ├── GameOverlay.kt           # Pause and game over overlays
│       └── ui/theme/                # Material Design theme
```

## Building and Running

### Prerequisites

- Android Studio Hedgehog or later
- Android SDK API 34 or higher
- Kotlin 1.9.0 or higher

### Steps

1. **Clone or download the project**
2. **Open in Android Studio**
3. **Sync Gradle files** (File → Sync Project with Gradle Files)
4. **Connect an Android device** or start an emulator
5. **Run the app** (Run → Run 'app')

### Alternative: Command Line

```bash
# Navigate to the snake directory
cd snake

# Build the project
./gradlew build

# Install on connected device
./gradlew installDebug

# Run the app
./gradlew runDebug
```

## Customization

### Game Speed
To adjust the game speed, modify the `delay(200)` value in `MainActivity.kt`:
- Lower values = faster gameplay
- Higher values = slower gameplay

### Board Size
To change the game board size, modify the constants in `GameEngine.kt`:
```kotlin
const val BOARD_WIDTH = 20
const val BOARD_HEIGHT = 20
```

### Colors
To customize the game colors, edit the color values in:
- `ui/theme/Color.kt` - Theme colors
- `GameBoard.kt` - Game board colors
- `GameControls.kt` - Control button colors

## Game Architecture

The game follows a clean architecture pattern:

1. **GameState**: Immutable data class holding all game state
2. **GameEngine**: Pure logic for game rules and state transitions
3. **UI Components**: Compose components for rendering and user interaction
4. **MainActivity**: Orchestrates the game loop and state management

## Performance

- Uses Compose Canvas for efficient 2D rendering
- Minimal state updates for smooth 60fps gameplay
- Efficient collision detection algorithms
- Memory-efficient data structures

## Future Enhancements

Potential improvements for future versions:
- Sound effects and background music
- High score persistence
- Multiple difficulty levels
- Power-ups and special food
- Multiplayer support
- Different snake skins
- Achievement system

## License

This project is open source and available under the MIT License.

## Contributing

Feel free to submit issues, feature requests, or pull requests to improve the game!
