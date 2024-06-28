package com.example.rock_paper_scissor_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rock_paper_scissor_game.databinding.ActivityEndComputerBinding
import com.example.rock_paper_scissor_game.databinding.ActivityPlayWithComputerBinding

class EndComputerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEndComputerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEndComputerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val winner = intent.getStringExtra("winner")
        setView(winner.toString())
        binding.goHome.setOnClickListener {
            finish()
        }
    }

    private fun setView(winner:String)
    {
        if(winner == "Computer"){
            binding.statusDisplay.setImageResource(R.drawable.lose_gif)
            binding.winner.text = "Better Luck Next Time !!"
        }
        else {
            binding.statusDisplay.setImageResource(R.drawable.win_gif)
            binding.winner.text = "Congratulations !!"
        }
    }
}