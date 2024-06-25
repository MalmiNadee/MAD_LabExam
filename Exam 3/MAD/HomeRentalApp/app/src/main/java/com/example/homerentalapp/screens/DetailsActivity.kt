package com.example.homerentalapp.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.homerentalapp.R



class DetailsActivity : AppCompatActivity() {

    private lateinit var  apply: Button
    private lateinit var  back: Button
    private lateinit var  update: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)


        apply = findViewById(R.id.button3)
        apply.setOnClickListener{
            startActivity( Intent(this@DetailsActivity, RegisterActivity::class.java) )
            finish()
        }

        update = findViewById(R.id.button10)
        update.setOnClickListener{
            startActivity( Intent(this@DetailsActivity, UpdateActivity::class.java) )
            finish()
        }

        back = findViewById(R.id.button18)
        back.setOnClickListener{
            startActivity( Intent(this@DetailsActivity, Home2Activity::class.java) )
            finish()
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}