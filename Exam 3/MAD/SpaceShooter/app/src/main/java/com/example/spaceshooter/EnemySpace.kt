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

class EnemySpace(private val context: Context) : AppCompatActivity() {

    private var enemySpace: Bitmap = BitmapFactory.decodeResource(context.resources,R.drawable.rocket)
    var  ex:Int = 0
    var  ey:Int = 0
    var  enemyVelocity:Int = 0
    private val random:Random

    init {
        resetEnemySpace()
        this.random = Random
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_enemy_space)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun getEnemySpaceship():Bitmap{
        return enemySpace
    }

    fun getEnemySpaceWidth():Int{
        return enemySpace.getWidth()
    }

    fun getEnemySpaceHeight():Int{
        return enemySpace.getHeight()
    }

    private fun resetEnemySpace() {
        ex =  200 + random.nextInt(400)
        ey =0
        enemyVelocity = 14+ random.nextInt(10)
    }
}
