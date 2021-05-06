package com.example.tictactoe

import android.util.Log
import com.example.tictactoe.api.GameService
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState

object GameManager {
    var player: String? = null
    var state: GameState? = null

    val StartingGameState: GameState = listOf(listOf(0, 0, 0), listOf(0, 0, 0), listOf(0, 0, 0))

    fun createGame(player: String) {
        GameService.createGame(player, StartingGameState) { game: Game?, err: Int? ->
            if (err != null) {
                Log.d("unsuccessful", "Could not create a game")
            } else {
                Log.d("successful", game?.gameId.toString())
            }
        }
    }


}