package com.example.snake

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

@Composable
fun GameBoard(
    gameState: GameState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color(0xFF2E7D32))
            .border(2.dp, Color(0xFF1B5E20))
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            val cellSize = size.width / GameEngine.BOARD_WIDTH
            
            // Draw grid
            drawGrid(cellSize)
            
            // Draw snake
            drawSnake(gameState.snake, cellSize)
            
            // Draw food
            drawFood(gameState.food, cellSize)
        }
    }
}

private fun DrawScope.drawGrid(cellSize: Float) {
    val gridColor = Color(0xFF1B5E20).copy(alpha = 0.3f)
    
    // Vertical lines
    for (i in 0..GameEngine.BOARD_WIDTH) {
        drawLine(
            color = gridColor,
            start = Offset(i * cellSize, 0f),
            end = Offset(i * cellSize, size.height),
            strokeWidth = 1f
        )
    }
    
    // Horizontal lines
    for (i in 0..GameEngine.BOARD_HEIGHT) {
        drawLine(
            color = gridColor,
            start = Offset(0f, i * cellSize),
            end = Offset(size.width, i * cellSize),
            strokeWidth = 1f
        )
    }
}

private fun DrawScope.drawSnake(snake: List<Position>, cellSize: Float) {
    snake.forEachIndexed { index, position ->
        val color = if (index == 0) {
            // Head
            Color(0xFF4CAF50)
        } else {
            // Body
            Color(0xFF66BB6A)
        }
        
        drawRect(
            color = color,
            topLeft = Offset(
                position.x * cellSize + 1f,
                position.y * cellSize + 1f
            ),
            size = Size(cellSize - 2f, cellSize - 2f)
        )
        
        // Draw eyes for head
        if (index == 0) {
            val eyeSize = cellSize * 0.15f
            val eyeOffset = cellSize * 0.25f
            
            drawCircle(
                color = Color.Black,
                radius = eyeSize,
                center = Offset(
                    position.x * cellSize + eyeOffset,
                    position.y * cellSize + eyeOffset
                )
            )
            
            drawCircle(
                color = Color.Black,
                radius = eyeSize,
                center = Offset(
                    position.x * cellSize + cellSize - eyeOffset,
                    position.y * cellSize + eyeOffset
                )
            )
        }
    }
}

private fun DrawScope.drawFood(food: Position, cellSize: Float) {
    val center = Offset(
        food.x * cellSize + cellSize / 2,
        food.y * cellSize + cellSize / 2
    )
    val radius = cellSize * 0.4f
    
    drawCircle(
        color = Color(0xFFE53935),
        radius = radius,
        center = center
    )
    
    // Add a highlight
    drawCircle(
        color = Color(0xFFFFCDD2),
        radius = radius * 0.3f,
        center = Offset(
            center.x - radius * 0.2f,
            center.y - radius * 0.2f
        )
    )
} 