package com.sygame.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ErgebnisActivity : AppCompatActivity() {

    lateinit var feedBack: TextView
    lateinit var ergebnis: TextView
    lateinit var playAgain: Button
    lateinit var quit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ergebnis)

        supportActionBar!!.title = "Ergebnis!"

        feedBack = findViewById(R.id.feedBack)
        ergebnis = findViewById(R.id.ergebnis)
        playAgain = findViewById(R.id.playAgain)
        quit = findViewById(R.id.quit)

        /*
        val score = intent.getIntExtra("score",0)
        ergebnis.text = "$score"
         */

        scoreFeedback()

        playAgain.setOnClickListener {
            val intent = Intent(this@ErgebnisActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        quit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    fun scoreFeedback(){
        val score = intent.getIntExtra("score",0)
        ergebnis.text = "$score"
        if (score == 100){
            feedBack.setText("Herzlichen Glückwunsch!\n" +
                    "Sie sind wunderbar!")
        }
        else if (score < 100 && score > 50){
            feedBack.setText("Herzlichen Glückwunsch!\n" +
                    "Sie sind sehr gut!")
        }
    }
}