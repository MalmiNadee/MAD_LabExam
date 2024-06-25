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
import com.example.homerentalapp.fragment.HomeFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var  createAccount: TextView
    private lateinit var  login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)


        createAccount = findViewById(R.id.textView3)
        createAccount.setOnClickListener{
                startActivity( Intent(this@LoginActivity,SignUpActivity::class.java) )
                finish()
        }
        login = findViewById(R.id.button4)
        login.setOnClickListener{
            startActivity( Intent(this@LoginActivity, Home2Activity::class.java) )
            Toast.makeText(this, "Login Successfully!", Toast.LENGTH_SHORT).show()
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }
}

