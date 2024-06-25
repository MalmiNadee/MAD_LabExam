package com.example.tutorial4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {


    lateinit var submit:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        submit = findViewById(R.id.button7)
       submit.setOnClickListener{
           startActivity(Intent(this,MainActivity3::class.java))
           Toast.makeText(this, " Successfully!", Toast.LENGTH_SHORT).show()
           finish()
       }
        enableEdgeToEdge()

//        fun sayHello(){
//            Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show()
//        }
//
//        fun GoodBye(){
//            Toast.makeText(this,"GoodBye",Toast.LENGTH_SHORT).show()
//        }
//
//        btnWelcome.setOnClickListener{
//            sayHello()
//        }
//        btnGoodBye.setOnClickListener{
//            GoodBye()
//        }
        

        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}