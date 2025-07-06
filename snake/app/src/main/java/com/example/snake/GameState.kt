package com.example.snake

import androidx.compose.runtime.Immutable

@Immutable
data class GameState(
    val snake: List<Position> = listOf(Position(5, 5)),
    val food: Position = Position(10, 10),
    val direction: Direction = Direction.RIGHT,
    val score: Int = 0,
    val isGameOver: Boolean = false,
    val isPaused: Boolean = false
)

@Immutable
data class Position(
    val x: Int,
    val y: Int
) {
    operator fun plus(other: Position): Position = Position(x + other.x, y + other.y)
}

enum class Direction(val offset: Position) {
    UP(Position(0, -1)),
    DOWN(Position(0, 1)),
    LEFT(Position(-1, 0)),
    RIGHT(Position(1, 0))
} 