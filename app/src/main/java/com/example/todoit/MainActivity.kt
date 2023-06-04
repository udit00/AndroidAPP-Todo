package com.example.todoit

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.todoit.common.base.BaseActivity
import com.example.todoit.ui.home.HomeActivity
import com.example.todoit.ui.login.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
//        openHomePage()
    }


    private fun openLogin(){
        val loginIntent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(loginIntent)
    }

    private fun openHomePage() {
        val homeIntent = Intent(this@MainActivity, HomeActivity::class.java)
        startActivity(homeIntent)
    }
}