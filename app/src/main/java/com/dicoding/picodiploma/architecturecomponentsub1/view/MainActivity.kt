package com.dicoding.picodiploma.architecturecomponentsub1.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.dicoding.picodiploma.architecturecomponentsub1.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(this@MainActivity, "Welcome!", Toast.LENGTH_SHORT).show()
            val intent= Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 800)
    }
}