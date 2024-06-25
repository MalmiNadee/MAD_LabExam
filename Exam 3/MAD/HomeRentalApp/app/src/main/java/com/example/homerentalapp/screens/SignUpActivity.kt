package com.example.homerentalapp.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.homerentalapp.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var haveAccount: TextView
    private lateinit var signUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        haveAccount = findViewById(R.id.textView6)
        haveAccount.setOnClickListener{
            startActivity( Intent(this@SignUpActivity,LoginActivity::class.java) )
            finish()
        }

        signUp = findViewById(R.id.button4)
        signUp.setOnClickListener{
            startActivity( Intent(this@SignUpActivity, LoginActivity::class.java) )
            Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show()
            finish()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}