package com.example.city.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.city.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.myLooper()!!).postDelayed({
            val splashScreen = Intent (this@SplashScreenActivity , LoginActivity::class.java)
            startActivity(splashScreen)
            finish()
        },4000)

    }
}