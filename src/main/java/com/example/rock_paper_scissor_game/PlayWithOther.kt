package com.example.rock_paper_scissor_game

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.rock_paper_scissor_game.databinding.ActivityPlayWithOtherBinding

class PlayWithOther : AppCompatActivity() {
    private lateinit var binding: ActivityPlayWithOtherBinding
    private var animation1:AnimationDrawable ?= null
    private var animation2:AnimationDrawable ?= null
    private var setTime:CountDownTimer ?= null
    private var player1Ready = false
    private var player2Ready = false
    private var allowPlaying = true
    private lateinit var selectionP1:String
    private lateinit var selectionP2:String
    private var pName1 = "PN1"
    private var pName2 = "PN2"
    private var score1 = 0
    private var score2 = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayWithOtherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getPlayerName()
        binding.rock2.setOnClickListener {
            onPlayer2("Rock")
        }
        binding.paper2.setOnClickListener {
            onPlayer2("Paper")
        }
        binding.scissor2.setOnClickListener {
            onPlayer2("Scissor")
        }

        binding.rock1.setOnClickListener {
            onPlayer1("Rock")
        }
        binding.paper1.setOnClickListener {
            onPlayer1("Paper")
        }
        binding.scissor1.setOnClickListener {
            onPlayer1("Scissor")
        }
    }

    private fun platAnimation()
    {
        binding.player1Choice.setImageResource(0)
        binding.player2Choice.setImageResource(0)
        binding.statusPlayer1.text = ""
        binding.statusPlayer2.text = ""
        binding.player1Choice.setBackgroundResource(R.drawable.animation)
        animation1 = binding.player1Choice.background as AnimationDrawable
        binding.player2Choice.setBackgroundResource(R.drawable.animation)
        animation2 = binding.player2Choice.background as AnimationDrawable
        setTime = object :CountDownTimer(3000 , 1000){
            override fun onTick(millisUntilFinished: Long) {
                animation1?.start()
                animation2?.start()
            }

            override fun onFinish() {
                animation1?.stop()
                animation2?.stop()
                allowPlaying = true
                player1Ready = false
                player2Ready = false
                binding.player1Choice.setBackgroundResource(0)
                binding.player2Choice.setBackgroundResource(0)
                setSelectedIcon()
                setScore()
                endGame()
            }

        }.start()
    }

    private fun setSelectedIcon(){
        when(selectionP1){
            "Rock" -> binding.player1Choice.setImageResource(R.drawable.rock)
            "Paper" -> binding.player1Choice.setImageResource(R.drawable.paper)
            "Scissor" -> binding.player1Choice.setImageResource(R.drawable.scissor)
        }

        when(selectionP2){
            "Rock" -> binding.player2Choice.setImageResource(R.drawable.rock)
            "Paper" -> binding.player2Choice.setImageResource(R.drawable.paper)
            "Scissor" -> binding.player2Choice.setImageResource(R.drawable.scissor)
        }
    }

    private fun onPlayer1(selection:String){
        if (allowPlaying){
            binding.player1Choice.setImageResource(R.drawable.check)
            binding.statusPlayer1.text = "Ready"
            selectionP1 = selection
            player1Ready = true
            if (player2Ready){
                allowPlaying = false
                platAnimation()
            }
        }
    }

    private fun onPlayer2(selection:String){
        if (allowPlaying){
            binding.player2Choice.setImageResource(R.drawable.check)
            binding.statusPlayer2.text = "Ready"
            selectionP2 = selection
            player2Ready = true
            if (player1Ready){
                allowPlaying = false
                platAnimation()
            }
        }
    }

    private fun getResult():String
    {
        return if (selectionP1 == selectionP2)
            "Tie"
        else if (selectionP1=="Rock" && selectionP2=="Scissor" || selectionP1=="Paper" && selectionP2=="Rock" || selectionP1=="Scissor" && selectionP2=="Paper")
            "p1"
        else
            "p2"

    }

    private fun setScore(){
        if(getResult() == "Tie"){
            binding.statusPlayer1.text = "Tie"
            binding.statusPlayer2.text = "Tie"
        }
        else if (getResult() == "p1"){
            binding.statusPlayer1.text = "Win"
            binding.statusPlayer2.text = "Lose"
            score1++
            when (score1){
                1 -> binding.player1Star1.setImageResource(R.drawable.star)
                2 -> binding.player1Star2.setImageResource(R.drawable.star)
                3 -> binding.player1Star3.setImageResource(R.drawable.star)
            }
        }
        else {
            binding.statusPlayer1.text = "Lose"
            binding.statusPlayer2.text = "Win"
            score2++
            when (score2){
                1 -> binding.player2Star1.setImageResource(R.drawable.star)
                2 -> binding.player2Star2.setImageResource(R.drawable.star)
                3 -> binding.player2Star3.setImageResource(R.drawable.star)
            }
        }
    }

    private fun getPlayerName()
    {
        val nameDialog = Dialog(this)
        nameDialog.setContentView(R.layout.player_name_dialog)
        nameDialog.findViewById<Button>(R.id.btnOK).setOnClickListener {
            val name1 = nameDialog.findViewById<EditText>(R.id.name1).text
            val name2 = nameDialog.findViewById<EditText>(R.id.name2).text
            if (name1.isNotEmpty() && name2.isNotEmpty()){
                pName1 = name1.toString()
                pName2 = name2.toString()
                binding.player1.text = pName1
                binding.player2.text = pName2
                nameDialog.cancel()
            }
            else{
                Toast.makeText(this , "Enter Both Players" , Toast.LENGTH_SHORT).show()
            }
        }
        nameDialog.show()
    }

    private fun endGame()
    {
        if (score1==3 || score2==3){
            var winner = if (score1==3)
                pName1
            else
                pName2
            val intent = Intent(this , EndActivity::class.java)
            intent.putExtra("name" , winner)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        setTime = null
    }

}