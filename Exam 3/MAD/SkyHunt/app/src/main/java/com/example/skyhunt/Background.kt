package com.example.skyhunt

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Background(screenX:Int, screenY:Int,res: Resources){

      var x:Int = 0
      var y:Int = 0
      var background:Bitmap


      init{
            background = BitmapFactory.decodeResource(res,R.drawable.background2)
            //resize to fit entire screen
            background = Bitmap.createScaledBitmap(background, screenX, screenY, false)
      }
}