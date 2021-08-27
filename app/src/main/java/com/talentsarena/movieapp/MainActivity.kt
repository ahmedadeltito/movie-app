package com.talentsarena.movieapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.talentsarena.movieapp.databinding.ActivityMainBinding

/**
 * Splash entry screen of our app.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        countDownTimer = object : CountDownTimer(3000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = (millisUntilFinished / 10).toInt()
                viewBinding.appPr.progress = viewBinding.appPr.max - progress
            }

            override fun onFinish() {
                startActivity(Intent(this@MainActivity, HomeScreenActivity::class.java))
                this@MainActivity.finish()
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}