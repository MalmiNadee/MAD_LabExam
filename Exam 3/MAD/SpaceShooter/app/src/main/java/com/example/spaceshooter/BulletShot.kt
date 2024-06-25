package com.example.spaceshooter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class BulletShot(context: Context, val shx: Int, var shy: Int):AppCompatActivity() {

    private val bulletShot: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.bulletshot)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bullet_shot)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun getShot():Bitmap{
        return bulletShot
    }
}