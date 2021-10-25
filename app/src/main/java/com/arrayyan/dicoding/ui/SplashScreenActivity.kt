package com.arrayyan.dicoding.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.arrayyan.core.ui.BaseActivity
import com.arrayyan.dicoding.databinding.ActivitySplashScreenBinding
import com.arrayyan.dicoding.ui.main.MainActivity
import kotlinx.coroutines.delay
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivitySplashScreenBinding
        get() = ActivitySplashScreenBinding::inflate

    override fun setup(savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            delay(3000)
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}