package com.example.todoit

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.todoit.base.BaseActivity
import com.example.todoit.ui.Login.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            start()
        }
    }
    private suspend fun start(){
        delay(3000)
        openLogin()
    }

    private fun openLogin(){
        val loginIntent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(loginIntent)
    }
}