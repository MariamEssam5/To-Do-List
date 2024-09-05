package com.example.finaltodo.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.finaltodo.R

class SplashActivity : AppCompatActivity() {
    private lateinit var logoImage: ImageView
    private lateinit var titleText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enable edge-to-edge layout if desired
        setContentView(R.layout.activity_spalsh) // Ensure this is the correct layout file

        // Load animations
        val fromTop = AnimationUtils.loadAnimation(this, R.anim.from_top)
        val fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom)

        // Find views
        logoImage = findViewById(R.id.logo)
        titleText = findViewById(R.id.title)

        // Start animations
        logoImage.startAnimation(fromTop)
        titleText.startAnimation(fromBottom)

        // Delay to show the splash screen before moving to MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // Duration of splash screen display (3000ms = 3 seconds)
    }
}
