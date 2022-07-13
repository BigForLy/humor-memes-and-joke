package com.example.humormemeandjoke

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.HumorMemeAndJoke_NoActionBar)
        super.onCreate(savedInstanceState)

        val chefIntent = Intent(this, StartHumorActivity::class.java)
        startActivity(chefIntent)
        finish()
    }
}