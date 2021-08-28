package com.talentsarena.movieapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.talentsarena.core.extensions.startCoroutineTimer
import com.talentsarena.movieapp.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi

/**
 * Splash entry screen of our app.
 */
@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    private val timer = startCoroutineTimer(delayMillis = 1500) {
        startActivity(Intent(this, HomeScreenActivity::class.java))
        this.finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}