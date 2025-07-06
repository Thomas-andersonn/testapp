package com.example.snake

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameControls(
    gameState: GameState,
    onDirectionChange: (Direction) -> Unit,
    onPauseToggle: () -> Unit,
    onRestart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Score display
        Text(
            text = "Score: ${gameState.score}",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Game control buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ControlButton(
                icon = if (gameState.isPaused) Icons.Default.PlayArrow else Icons.Default.Pause,
                onClick = onPauseToggle,
                enabled = !gameState.isGameOver
            )
            
            ControlButton(
                icon = Icons.Default.Refresh,
                onClick = onRestart
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Direction controls
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Up button
            DirectionButton(
                icon = Icons.Default.KeyboardArrowUp,
                onClick = { onDirectionChange(Direction.UP) },
                enabled = !gameState.isGameOver && !gameState.isPaused
            )
            
            // Left and Right buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DirectionButton(
                    icon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    onClick = { onDirectionChange(Direction.LEFT) },
                    enabled = !gameState.isGameOver && !gameState.isPaused
                )
                
                DirectionButton(
                    icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    onClick = { onDirectionChange(Direction.RIGHT) },
                    enabled = !gameState.isGameOver && !gameState.isPaused
                )
            }
            
            // Down button
            DirectionButton(
                icon = Icons.Default.KeyboardArrowDown,
                onClick = { onDirectionChange(Direction.DOWN) },
                enabled = !gameState.isGameOver && !gameState.isPaused
            )
        }
    }
}

@Composable
private fun ControlButton(
    icon: ImageVector,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(
                if (enabled) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            )
            .clickable(enabled = enabled) { onClick() }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (enabled) MaterialTheme.colorScheme.onPrimary
                   else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
private fun DirectionButton(
    icon: ImageVector,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(
                if (enabled) MaterialTheme.colorScheme.secondary
                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            )
            .clickable(enabled = enabled) { onClick() }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (enabled) MaterialTheme.colorScheme.onSecondary
                   else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
            modifier = Modifier.size(24.dp)
        )
    }
} 