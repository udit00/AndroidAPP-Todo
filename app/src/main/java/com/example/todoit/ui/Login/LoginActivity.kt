package com.example.todoit.ui.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoit.R
import com.example.todoit.base.BaseActivity
import com.example.todoit.databinding.ActivityLoginBinding
import com.example.todoit.utils.logger
import kotlin.math.log

class LoginActivity : BaseActivity() {

    private var username: String = ""
    private var password: String = ""
    private lateinit var activityLoginBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(activityLoginBinding.root)
        onClicks()
    }

    private fun onClicks(){
        activityLoginBinding.signin.setOnClickListener {
            if(activityLoginBinding.username.text.toString().isNullOrEmpty()) {
                errorToast("Please enter Username.")
                return@setOnClickListener
            } else if(activityLoginBinding.password.text.toString().isNullOrEmpty()) {
                errorToast("Please enter Password.")
                return@setOnClickListener
            } else {
                attemptLogin(
                    activityLoginBinding.username.text.toString().uppercase(),
                    activityLoginBinding.password.text.toString().uppercase()
                )
            }
        }
    }

    private fun attemptLogin(username: String, password: String){
        successToast("Login Successful.")
    }
}