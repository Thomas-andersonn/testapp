package com.example.snake

import kotlin.random.Random

class GameEngine {
    companion object {
        const val BOARD_WIDTH = 20
        const val BOARD_HEIGHT = 20
    }

    fun updateGame(currentState: GameState): GameState {
        if (currentState.isGameOver || currentState.isPaused) {
            return currentState
        }

        val newHead = currentState.snake.first() + currentState.direction.offset
        
        // Check wall collision
        if (newHead.x < 0 || newHead.x >= BOARD_WIDTH || 
            newHead.y < 0 || newHead.y >= BOARD_HEIGHT) {
            return currentState.copy(isGameOver = true)
        }
        
        // Check self collision
        if (currentState.snake.contains(newHead)) {
            return currentState.copy(isGameOver = true)
        }
        
        val newSnake = mutableListOf(newHead)
        newSnake.addAll(currentState.snake)
        
        // Check if food is eaten
        val foodEaten = newHead == currentState.food
        if (foodEaten) {
            val newFood = generateFood(newSnake)
            return currentState.copy(
                snake = newSnake,
                food = newFood,
                score = currentState.score + 10
            )
        } else {
            // Remove tail if no food eaten
            newSnake.removeAt(newSnake.size - 1)
            return currentState.copy(snake = newSnake)
        }
    }
    
    private fun generateFood(snake: List<Position>): Position {
        val availablePositions = mutableListOf<Position>()
        
        for (x in 0 until BOARD_WIDTH) {
            for (y in 0 until BOARD_HEIGHT) {
                val position = Position(x, y)
                if (!snake.contains(position)) {
                    availablePositions.add(position)
                }
            }
        }
        
        return availablePositions.random(Random)
    }
    
    fun changeDirection(currentState: GameState, newDirection: Direction): GameState {
        // Prevent 180-degree turns
        val isOpposite = when (currentState.direction) {
            Direction.UP -> newDirection == Direction.DOWN
            Direction.DOWN -> newDirection == Direction.UP
            Direction.LEFT -> newDirection == Direction.RIGHT
            Direction.RIGHT -> newDirection == Direction.LEFT
        }
        
        return if (!isOpposite) {
            currentState.copy(direction = newDirection)
        } else {
            currentState
        }
    }
    
    fun resetGame(): GameState {
        return GameState(
            snake = listOf(Position(5, 5)),
            food = Position(10, 10),
            direction = Direction.RIGHT,
            score = 0,
            isGameOver = false,
            isPaused = false
        )
    }
    
    fun togglePause(currentState: GameState): GameState {
        return currentState.copy(isPaused = !currentState.isPaused)
    }
} 