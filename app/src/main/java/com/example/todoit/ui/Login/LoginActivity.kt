package com.example.todoit.ui.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoit.R
import com.example.todoit.base.BaseActivity
import com.example.todoit.databinding.ActivityLoginBinding

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
            activityLoginBinding.getUsername()?.let {
                    errorToast(username)
            }
//            errorToast(activityLoginBinding.getPassword())
//            if(activityLoginBinding.username.toString().isNullOrEmpty()) {
//                errorToast("Please enter Username.")
//                return@setOnClickListener
//            } else if(activityLoginBinding.password.toString().isNullOrEmpty()) {
//                errorToast("Please enter Password.")
//                return@setOnClickListener
//            } else {
//                attemptLogin(
//                    activityLoginBinding.username.toString(),
//                    activityLoginBinding.password.toString()
//                )
//            }
        }
    }

    private fun attemptLogin(username: String, password: String){
        successToast("Login Successful.")
    }
}