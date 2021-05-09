package com.example.tictactoe

import android.util.Log
import com.example.tictactoe.api.GameService
import com.example.tictactoe.api.data.Game
import com.example.tictactoe.api.data.GameState

object GameManager {
    val TAG:String = "GameManager"

    var player: String? = null
    var game: Game? = null

    val StartingGameState: GameState = listOf(listOf(0, 0, 0), listOf(0, 0, 0), listOf(0, 0, 0))

    fun createGame(player: String) {
        GameService.createGame(player, StartingGameState) { game: Game?, err: Int? ->
            if (err != null) {
                Log.d(TAG, "Could not create a game")
            } else {
                this.game = game
                Log.d(TAG, "Created game with game id: ${this.game?.gameId}")
            }
        }
    }

    fun joinGame(player: String, gameId: String) {
        GameService.joinGame(player, gameId) { game: Game?, err: Int? ->
            if (err != null) {
                Log.d(TAG, "Could not join game")
            } else {
                this.game = game
                Log.d(
                    TAG, "Joined game with game id: ${this.game?.gameId}"
                )
            }
        }
    }

    fun pollGame() {
        var gameId = this.game?.gameId

        if (gameId == null) {
            Log.d(TAG, "game id is null, could not poll game.")
        } else {
            // gameId exists, poll game
            GameService.pollGame(gameId) { game: Game?, err: Int? ->
                if (err != null) {
                    Log.d( TAG, "Could not poll game")
                } else {
                    Log.d(TAG, this.game.toString())

                    if (this.game?.state != game?.state) {
                        // game state has changed, reinitialize game.
                        this.game = game
                        // this.game?.state = game!!.state
                        Log.d(TAG, "Polled game, new state: ${this.game?.state}")
                    }
                }
            }
        }
    }


}