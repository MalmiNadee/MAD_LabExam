package com.example.skyhunt

import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    private lateinit var  gameView:GameView

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val point = Point()

        // Make the activity full-screen
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )

            display?.getSize(point)
        }

        val width = if (point.x > 0) point.x else 0
        val height = if (point.y > 0) point.y else 0

        gameView = GameView(this, width, height)
        setContentView(gameView)
    }


    override fun onPause(){
        super.onPause()
        gameView.pause()
    }

    override fun onResume(){
        super.onResume()
        gameView.resume()
    }
}