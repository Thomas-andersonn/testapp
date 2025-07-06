package com.example.snake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.snake.ui.theme.SnakeTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnakeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SnakeGame()
                }
            }
        }
    }
}

@Composable
fun SnakeGame() {
    var gameState by remember { mutableStateOf(GameEngine().resetGame()) }
    val gameEngine = remember { GameEngine() }
    
    // Game loop
    LaunchedEffect(Unit) {
        while (true) {
            delay(200) // Game speed - lower number = faster
            gameState = gameEngine.updateGame(gameState)
        }
    }
    
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // Game board
            GameBoard(
                gameState = gameState,
                modifier = Modifier.weight(1f)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Game controls
            GameControls(
                gameState = gameState,
                onDirectionChange = { direction ->
                    gameState = gameEngine.changeDirection(gameState, direction)
                },
                onPauseToggle = {
                    gameState = gameEngine.togglePause(gameState)
                },
                onRestart = {
                    gameState = gameEngine.resetGame()
                }
            )
        }
        
        // Game overlay (pause/game over messages)
        if (gameState.isGameOver || gameState.isPaused) {
            GameOverlay(gameState = gameState)
        }
    }
}