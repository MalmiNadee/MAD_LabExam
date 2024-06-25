package com.example.spaceshooter

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Start : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start)
    }

    fun startGame(view: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}