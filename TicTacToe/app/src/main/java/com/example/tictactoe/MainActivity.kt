package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.tictactoe.api.data.GameState
import com.example.tictactoe.databinding.ActivityMainBinding
import com.example.tictactoe.dialogs.CreateGameDialog
import com.example.tictactoe.dialogs.GameDialogListener
import com.example.tictactoe.dialogs.JoinGameDialog

class MainActivity : AppCompatActivity(), GameDialogListener {
    lateinit var binding: ActivityMainBinding
    val TAG:String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if we can create a game
        GameManager.createGame("Player1")
        // gameid to use:tg4et

        // Check if we can join a game.
        // GameManager.joinGame("Player2", "t32ddi")

        // Check if game is polled
        // GameManager.pollGame()

        // Check if game can be updated
        val newGameState: GameState = listOf(listOf('X', '0', '0'), listOf('X', 'O', '0'), listOf('X', 'O', '0'))
        GameManager.updateGame(newGameState)

        binding.startGameButton.setOnClickListener {
            createNewGame()
        }

        binding.joinGameButton.setOnClickListener {
            joinGame()
        }

    }

    private fun createNewGame(){
        val dlg = CreateGameDialog()
        dlg.show(supportFragmentManager,"CreateGameDialogFragment")
    }

    private fun joinGame(){
        val dlg = JoinGameDialog()
        dlg.show(supportFragmentManager,"JoinDialogFragment")

    }

    override fun onDialogCreateGame(player: String) {
        Log.d(TAG,player)
    }

    override fun onDialogJoinGame(player: String, gameId: String) {
        Log.d(TAG, "$player $gameId")
    }
}