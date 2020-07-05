package com.example.ourapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {

    lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)

        logo.startAnimation(AnimationUtils.loadAnimation(this,R.anim.top_anim))
        textView1.startAnimation(AnimationUtils.loadAnimation(this,R.anim.bottom_anim))
        textView1a.startAnimation(AnimationUtils.loadAnimation(this,R.anim.bottom_anim))
        textView2.startAnimation(AnimationUtils.loadAnimation(this,R.anim.bottom_anim))

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashScreen,login::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}
