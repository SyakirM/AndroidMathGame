package com.sygame.mathgame

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var addition: Button
    lateinit var substraction: Button
    lateinit var multiplication: Button

    var keyAddition = 1
    var keySubstraction = 2
    var keyMultiplication = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addition = findViewById(R.id.addition)
        substraction = findViewById(R.id.subtraction)
        multiplication = findViewById(R.id.multiplication)

        addition.setOnClickListener {
            val intent = Intent(this@MainActivity,AdditionActivity::class.java)
            intent.putExtra("key",keyAddition)
            startActivity(intent)
        }

        substraction.setOnClickListener {
            val intent = Intent(this@MainActivity,AdditionActivity::class.java)
            intent.putExtra("key",keySubstraction)
            startActivity(intent)
        }
        multiplication.setOnClickListener {
            val intent = Intent(this@MainActivity,AdditionActivity::class.java)
            intent.putExtra("key",keyMultiplication)
            startActivity(intent)
        }



    }
}