package com.example.tutorial4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {

    lateinit var editName :EditText
    lateinit var btnWelcome :Button
    lateinit var btnGoodBye :Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)


        editName =findViewById(R.id.editName)
        btnWelcome = findViewById(R.id.btnWelcome)
        btnGoodBye = findViewById(R.id.btnGoodBye)

        btnWelcome.setOnClickListener {
             welcome()
        }

        btnGoodBye.setOnClickListener {
            goodBye()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun welcome(){
        Toast.makeText(this,"${getText(R.string.msg1)} ${editName.text.toString()}",Toast.LENGTH_LONG).show()

    }
    fun goodBye(){
        Toast.makeText(this,"${getText(R.string.msg2)}  ${editName.text.toString()}",Toast.LENGTH_LONG).show()
    }
}