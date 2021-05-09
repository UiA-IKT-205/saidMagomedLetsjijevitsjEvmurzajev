package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if we can create a game
        // GameManager.createGame("Player1")
        // gameid to use:tg4et

        // Check if we can join a game.
        // GameManager.joinGame("Player2", "t32ddi")

        // Check if game is polled
        GameManager.pollGame()
    }
}