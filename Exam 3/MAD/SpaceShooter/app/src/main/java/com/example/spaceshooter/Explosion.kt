package com.example.spaceshooter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Explosion(context:Context, eX: Int, eY: Int) : AppCompatActivity() {

    val explosion = ArrayList<Bitmap>(9)
    var explosionFrame:Int = 0
    var eX:Int = 0
    var eY:Int = 0

    init {
        explosion[0] = BitmapFactory.decodeResource(context.resources,R.drawable.explosion1)
        explosion[1] = BitmapFactory.decodeResource(context.resources,R.drawable.explosion2)
        explosion[2] = BitmapFactory.decodeResource(context.resources,R.drawable.explosion3)
        explosion[3] = BitmapFactory.decodeResource(context.resources,R.drawable.explosion4)
        explosionFrame = 0;
        this.eX = eX
        this.eY = eY
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_explosion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun getExplosion(explosionFrame:Int):Bitmap{
        return explosion[explosionFrame]
    }
}



