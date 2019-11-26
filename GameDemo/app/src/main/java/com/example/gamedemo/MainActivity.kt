package com.example.gamedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    var score : Int = 0
    var imageArray = ArrayList<ImageView>()
    var handler : Handler = Handler()
    var runnable : Runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        score = 0
        imageArray = arrayListOf(imageView, imageView2, imageView3, imageView4,
            imageView5, imageView6, imageView7, imageView8, imageView9)

        hideImage()

        object : CountDownTimer(20000,1000) {
            override fun onFinish() {
                timeText.text = "Time's off"
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
            }

            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "Time: " + millisUntilFinished/1000
            }
        }.start()
    }

    fun hideImage() {

        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val index = random.nextInt(8-0)
                imageArray[index].visibility = View.VISIBLE

                handler.postDelayed(runnable, 500)
            }
        }

        handler.post(runnable)

    }

    fun increaseScore(view: View) {
        score++
        scoreText.text = "Score: " + score
    }
}
