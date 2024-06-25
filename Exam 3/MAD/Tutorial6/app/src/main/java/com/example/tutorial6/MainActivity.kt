package com.example.tutorial6

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.tutorial6.viewmodels.MainActivityData

class MainActivity : AppCompatActivity() {

    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val textView:TextView =findViewById(R.id.textView)
        val button:Button = findViewById(R.id.button2)
        //bind object
        val viewModel = ViewModelProvider(this)[MainActivityData::class.java]
        //textView.setText(count.toString())
        textView.text = viewModel.count.value.toString()

        button.setOnClickListener {
           // count++
           // textView.setText(count.toString())
           // Thread.sleep(2000)
            viewModel.increment()
        }

        //observing values from viewModel
        viewModel.count.observe(this){
            textView.text = it.toString()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}