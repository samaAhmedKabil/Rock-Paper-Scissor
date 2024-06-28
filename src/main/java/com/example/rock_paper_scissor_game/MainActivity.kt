package com.example.rock_paper_scissor_game

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.rock_paper_scissor_game.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        playWithOtherClick()
        playWithComputerClick()
        seeInstructionClick()
    }

    private fun playWithOtherClick(){
        binding.playWithOther.setOnClickListener {
            val intent = Intent(this , PlayWithOther::class.java)
            startActivity(intent)
        }
    }

    private fun playWithComputerClick(){
        binding.playWithComputer.setOnClickListener {
            val intent = Intent(this , PlayWithComputer::class.java)
            startActivity(intent)
        }
    }

    private fun seeInstructionClick(){
        binding.seeInstruction.setOnClickListener {
            showInstructions()
        }
    }

    private fun showInstructions(){
        val instDialog = Dialog(this)
        instDialog.setContentView(R.layout.instruction_dialog)
        instDialog.findViewById<Button>(R.id.btnOK).setOnClickListener {
            instDialog.cancel()
        }
        instDialog.show()
    }
}