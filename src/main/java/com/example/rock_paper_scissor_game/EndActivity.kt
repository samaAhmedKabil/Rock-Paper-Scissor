package com.example.rock_paper_scissor_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rock_paper_scissor_game.databinding.ActivityEndBinding
import com.example.rock_paper_scissor_game.databinding.ActivityPlayWithOtherBinding

class EndActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEndBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEndBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.playerName.text = intent.getStringExtra("name")
        binding.goHome.setOnClickListener {
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}