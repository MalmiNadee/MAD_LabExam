package com.example.spaceshooter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.os.Handler
import android.view.Display
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class SpaceShooter(context: Context) :View(context){

    private lateinit var background: Bitmap
    private lateinit var lifeImage:Bitmap
    @get:JvmName("getCustomHandler")
    var handler:Handler? = Handler()
    private val UPDATE_MILLIS:Long = 30
    companion object{
        var screenWidth:Int = 0
        var screenHeight:Int = 0
    }
    private var  points:Int = 0
    private var life:Int = 3
    private lateinit var  scorePaint: Paint
    private val TEXT_SIZE:Float = 80f
    private var paused:Boolean = false
    private lateinit var ourSpace:OurSpace
    private lateinit var enemySpace:EnemySpace
    private val random: Random = TODO()
    private var enemyShots:ArrayList<BulletShot> = ArrayList()
    private var ourShots:ArrayList<BulletShot> = ArrayList()
    private var enemyExplosion:Boolean = false
    private var explosion:Explosion
    private var explosions:ArrayList<Explosion>  = ArrayList()
    private var enemyShotAction:Boolean = false
    private val runnable:Runnable = Runnable(){
//         fun run(){
//             invalidate()
//         }
    }

    init {
        enemyShots = ArrayList()
        ourShots = ArrayList()
        explosions = ArrayList()
        val display:Display = (context as Activity).windowManager.defaultDisplay
        val size =  Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
        ourSpace =  OurSpace(context)
        enemySpace =  EnemySpace(context)
        background = BitmapFactory.decodeResource(context.resources,R.drawable.background)
        lifeImage = BitmapFactory.decodeResource(context.resources,R.drawable.life)

        scorePaint =  Paint().apply {
            color = Color.RED
            textSize = TEXT_SIZE
            textAlign = Paint.Align.LEFT
        }
    }



    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(background,0f,0f,null)
        canvas.drawText("Pt : "+ points,0f,TEXT_SIZE,scorePaint)
        for( i in life downTo 1){
            canvas.drawBitmap(lifeImage, screenWidth.toFloat() - lifeImage.width * i , 0f ,null)
        }
        if(life==0){
            paused = true
            handler = null
//            val intent = Intent(context,GameOver::class.java)
//            intent.putExtra("points",points)
//            context.startActivity(intent)
            (context as Activity) .finish()
        }

        enemySpace.ex += enemySpace.enemyVelocity
        if(enemySpace.ex + enemySpace.getEnemySpaceWidth() >= screenWidth){
            enemySpace.enemyVelocity *= -1
        }
        if(enemySpace.ex <= 0 ){
            enemySpace.enemyVelocity *= -1
        }
        if((enemyShotAction == false) && (enemySpace.ex >= 200 + random.nextInt(400))){
            val enemyShot =  BulletShot(context,enemySpace.ex + enemySpace.getEnemySpaceWidth()/2,enemySpace.ey)
            enemyShots.add(enemyShot)
            enemyShotAction = true

        }
        // if enemyExplosion not true
        if(!enemyExplosion){
            canvas.drawBitmap(enemySpace.getEnemySpaceship(),enemySpace.ex.toFloat(),enemySpace.ey.toFloat(),null)
        }

        //if true ours pace not touch left and right edges of screen
        if(ourSpace.isAlive){
            if(ourSpace.ox > screenWidth - ourSpace.getOurSpaceWidth()){
                ourSpace.ox = screenWidth - ourSpace.getOurSpaceWidth()
            }else if(ourSpace.ox < 0){
                ourSpace.ox = 0
            }
            //draw our space on canvas
            canvas.drawBitmap(ourSpace.getOurSpace(),ourSpace.ox.toFloat(),ourSpace.oy.toFloat(),null)
        }
        for(i in 0 until enemyShots.size){
            enemyShots.get(i).shy += 15
            canvas.drawBitmap(enemyShots.get(i).getShot(), enemyShots.get(i).shx.toFloat(),enemyShots.get(i).shy.toFloat(),null)
            //if enemy shot collision with our space  decrement life  by 1
            if((enemyShots.get(i).shx >= ourSpace.ox) && (enemyShots.get(i).shx <=  ourSpace.ox + ourSpace.getOurSpaceWidth())
                && (enemyShots.get(i).shy >= ourSpace.oy) && (enemyShots.get(i).shy <=  screenHeight)){
                life --
                enemyShots.removeAt(i)
                explosion = Explosion(context,ourSpace.ox,ourSpace.oy)
                explosions.add(explosion)
            }else if(enemyShots.get(i).shy >= screenHeight){
                enemyShots.removeAt(i)
            }
            if(enemyShots.size == 0){
                enemyShotAction = false
            }

        }
        for(i in 0 until ourShots.size){
            ourShots.get(i).shy -= 15
            canvas.drawBitmap(ourShots.get(i).getShot(), ourShots.get(i).shx.toFloat(),ourShots.get(i).shy.toFloat(),null)
            //if collision detected with our points  increment   by 1
            if((ourShots.get(i).shx >= enemySpace.ex) && (ourShots.get(i).shx <=  enemySpace.ex + enemySpace.getEnemySpaceWidth())
                && (ourShots.get(i).shy <= enemySpace.getEnemySpaceHeight()) && (ourShots.get(i).shy >=  enemySpace.ey)){
                points++
                ourShots.removeAt(i)
                explosion = Explosion(context,enemySpace.ex,enemySpace.ey)
                explosions.add(explosion)
            }else if(ourShots.get(i).shy <= 0){
                ourShots.removeAt(i)
            }
        }

        for(i in 0 until explosions.size){
            canvas.drawBitmap(
                explosions.get(i).getExplosion(explosions.get(i).explosionFrame)!!,
                explosions.get(i).eX.toFloat(),explosions.get(i).eY.toFloat(),null)

            explosions.get(i).explosionFrame++
            if(explosions.get(i).explosionFrame > 8) {
                explosions.removeAt(i)

            }
            if(!paused){
                handler?.postDelayed(runnable,UPDATE_MILLIS)
            }
        }

        fun onTouchEvent(event: MotionEvent):Boolean{
            val touchX = event.getX().toInt()
            if(event.getAction() == MotionEvent.ACTION_UP){
                if(ourShots.size <3){
                    val ourShot =  BulletShot(context,ourSpace.ox + ourSpace.getOurSpaceWidth()/2, ourSpace.oy)
                    ourShots.add(ourShot)
                }
            }
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                ourSpace.ox = touchX
            }
            if(event.getAction() == MotionEvent.ACTION_MOVE){  //move finger on screen
                ourSpace.ox = touchX
            }
            return true
        }
    }
}
