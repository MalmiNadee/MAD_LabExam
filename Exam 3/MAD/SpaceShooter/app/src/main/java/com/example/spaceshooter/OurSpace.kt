package com.example.spaceshooter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlin.random.Random

class OurSpace(private val context: Context) : AppCompatActivity() {

    private var ourSpace: Bitmap = BitmapFactory.decodeResource(context.resources,R.drawable.flyingsaucer)
    var  ox:Int = 0
    var  oy:Int = 0
    var isAlive:Boolean = true
    private var  ourVelocity:Int = 0
    private val random: Random

    init {
        resetOurSpace()
        this.random = Random
    }

    private fun resetOurSpace() {
        ox =  200 + random.nextInt(SpaceShooter.screenWidth)
        oy = SpaceShooter.screenHeight - ourSpace.getHeight()
        ourVelocity = 10 + random.nextInt(6)
    }

    fun getOurSpace(): Bitmap {
        return ourSpace
    }

    fun getOurSpaceWidth():Int{
        return ourSpace.getWidth()
    }

    fun getOurSpaceHeight():Int{
        return ourSpace.getHeight()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_our_space)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




    }
}


