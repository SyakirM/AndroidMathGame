package com.sygame.mathgame

import android.content.Intent
import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.random.Random

class AdditionActivity : AppCompatActivity() {

    lateinit var score: TextView
    lateinit var life: TextView
    lateinit var time: TextView

    lateinit var aufGabe: TextView
    lateinit var antwort: EditText

    lateinit var buttonOk: Button
    lateinit var buttonNext: Button

    var richtigAntwort = 0
    var userScore = 0
    var userLife = 3

    lateinit var timer: CountDownTimer
    private val startTimerInMilli: Long = 10000
    var timeLeft: Long = startTimerInMilli

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addition)

        supportActionBar!!.title = "Lost Geht!"

        score = findViewById(R.id.score)
        life = findViewById(R.id.life)
        time = findViewById(R.id.time)

        aufGabe = findViewById(R.id.aufGabe)
        antwort = findViewById(R.id.antwort)

        buttonOk = findViewById(R.id.buttonOke)
        buttonNext = findViewById(R.id.buttonNext)

        gameContinue()

        buttonOk.setOnClickListener {
            val input = antwort.text.toString()
            if (input == ""){
                Toast.makeText(applicationContext, "Gebe Sie Ihre Antwort ein", Toast.LENGTH_SHORT).show()
            }
            else{
                pauseTimer()
                val userAntwort = input.toInt()
                if (userAntwort == richtigAntwort){
                    userScore = userScore + 10
                    aufGabe.setText("Herzlichen Glückwunsch! Ihre Antwort ist Richtig!")
                    score.text = userScore.toString()
                }
                else{
                    userLife--
                    aufGabe.setText("Tut mir leid, Ihre Antwort ist falsch!")
                    life.text = userLife.toString()

                }

            }
        }
        buttonNext.setOnClickListener {
            pauseTimer()
            resetTimer()
            antwort.setText("")

            if (userLife == 0){
                Toast.makeText(applicationContext, "Das Spiel ist aus", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@AdditionActivity,ErgebnisActivity::class.java)
                intent.putExtra("score",userScore)
                startActivity(intent)
                finish()
            }
            else if (userScore == 100){
                Toast.makeText(applicationContext, "Sie haben gewonnen!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@AdditionActivity,ErgebnisActivity::class.java)
                intent.putExtra("score",userScore)
                startActivity(intent)
                finish()
            }
            else{
                gameContinue()
            }
        }

    }

    fun gameContinue(){

        val key = intent.getIntExtra("key",0)
        var getKey: Int = key

        var nummber1 = Random.nextInt(0,100)
        var nummber2 = Random.nextInt(0,100)

        if (getKey == 1){
            aufGabe.text = "$nummber1 + $nummber2"
            richtigAntwort = nummber1 + nummber2
        }
        else if (getKey == 2){
            if (nummber1 >= nummber2){
                aufGabe.text = "$nummber1 - $nummber2"
                richtigAntwort = nummber1 - nummber2
            }
            else{
                aufGabe.text = "$nummber2 - $nummber1"
                richtigAntwort = nummber2 - nummber1
            }
        }
        else{
            aufGabe.text = "$nummber1 * $nummber2"
            richtigAntwort = nummber1 * nummber2
        }
        startTimer()
    }

    fun startTimer(){
        timer = object: CountDownTimer(timeLeft,1000){
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()
                userLife--
                aufGabe.setText("Die Zeit ist um!")
                life.text = userLife.toString()
            }

        }.start()
    }

    fun pauseTimer(){
        timer.cancel()
    }

    fun resetTimer(){

        timeLeft = startTimerInMilli
        updateText()
    }

    fun updateText(){
        val remainingTime: Int = (timeLeft/1000).toInt()
        time.text = String.format(Locale.getDefault(),"%02d",remainingTime)
    }
}