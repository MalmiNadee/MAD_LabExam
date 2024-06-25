package com.example.skyhunt

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
//import android.view.MotionEvent
import android.view.SurfaceView

@SuppressLint("ViewConstructor")
class GameView(context: Context, screenX: Int, screenY: Int) : SurfaceView(context), Runnable{

    private var thread:Thread? = null
    private  var isPlaying:Boolean = false
    private var screenX:Int = 0
    private var screenY:Int = 0
    //private  var flight: Flight
    private  var background1:Background
    private  var background2:Background
    companion object{
        var  screenRatioX:Float =0f
        var  screenRatioY:Float =0f
    }
    private  var paint: Paint = Paint()



    init {
        this.screenX =screenX
        this.screenY = screenY
        screenRatioX = 1920f/ screenX
        screenRatioY = 1080f/ screenY
        //flight = Flight(screenY , resources)
        background1 = Background(screenX, screenY, resources)
        background2 = Background(screenX, screenY, resources)
        background2.x = screenX
    }

    override fun run() {
        while(isPlaying){
            update()
            draw()
            sleep()
        }
    }
     private fun update(){


        background1.x -= (10 * screenRatioX).toInt()
        background2.x -= (10 * screenRatioX).toInt()

        if(background1.x + background1.background.width < 0 ){
            background1.x = screenX
        }
        if(background2.x + background2.background.width < 0 ){
            background2.x = screenX
        }
//        if (flight.isGoingUp){
//            flight.y -= (30 * screenRatioY).toInt()
//        }else{
//            flight.y += (30 * screenRatioY).toInt()
//        }
//        //to flight not go off the screen
//        if(flight.y < 0){
//            flight.y = 0
//        }
//        //if true flight  go off the screen at bottom
//        if(flight.y > screenY - flight.height){
//            flight.y = screenY - flight.height
//        }

    }
    private fun draw(){

        if(holder.surface.isValid){
            val canvas:Canvas = holder.lockCanvas()
            canvas.drawBitmap(background1.background, background1.x.toFloat(), background1.y.toFloat(), paint)
            canvas.drawBitmap(background2.background, background2.x.toFloat(), background2.y.toFloat(), paint)
            //canvas.drawBitmap(flight.getFlight(), flight.x.toFloat(), flight.y.toFloat(), paint)


            holder.unlockCanvasAndPost(canvas)
            postInvalidate()  // Call postInvalidate() to trigger drawing
        }

    }

    private fun sleep(){
          try {
              Thread.sleep(30)
          }catch (e:InterruptedException){
              e.printStackTrace()
          }
    }

    //resume or start the game
    fun resume(){
        isPlaying = true
        thread = Thread(this)
        thread?.start()
    }

    //pause the game
    fun pause(){
        try{
            isPlaying = false
            thread?.join()
        }catch (e:InterruptedException){
            e.printStackTrace()
        }

    }

//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        when(event?.action){
//            MotionEvent.ACTION_DOWN ->{
//                if(event.x < screenX/2){
//                    flight.isGoingUp = true
//                }
//            }
//            MotionEvent.ACTION_UP -> {
//                flight.isGoingUp = false
//                performClick()  // Call performClick for accessibility
//            }
//
//        }
//        return true
//    }
    override fun performClick(): Boolean {
        super.performClick()
        // Handle the click event here, if needed
        return true
    }
}