package com.example.tictactoe

import android.util.Log
import com.example.tictactoe.api.GameService
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState

object GameManager {
    var player: String? = null
    var gameState: Game? = null

    val StartingGameState: GameState = listOf(listOf(0, 0, 0), listOf(0, 0, 0), listOf(0, 0, 0))

    fun createGame(player: String) {
        GameService.createGame(player, StartingGameState) { game: Game?, err: Int? ->
            if (err != null) {
                Log.d("unsuccessful", "Could not create a game")
            } else {
                gameState = game
                Log.d("successful", game?.gameId.toString())
            }
        }
    }

    fun joinGame(player: String, gameId: String) {
        GameService.joinGame(player, gameId) { game: Game?, err: Int? ->
            if (err != null) {
                Log.d("unsuccessful", "Could not join game")
            } else {
                gameState = game
                Log.d("successfully joined game", gameState?.gameId.toString() + " "
                + gameState?.players)
            }
        }

    }


}