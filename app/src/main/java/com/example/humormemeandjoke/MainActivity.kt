package com.example.humormemeandjoke

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setButtonActivity()
    }

    private fun setButtonActivity() {
        val startButton = findViewById<Button>(R.id.buttonToStart)
        startButton.setOnClickListener{
            startButton.isClickable = false

            val chefIntent = Intent(this, StartHumorActivity::class.java)
            startActivity(chefIntent)
        }
    }
}