package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tictactoe.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    lateinit var binding: ActivityGameBinding
    val TAG:String = "GameActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}