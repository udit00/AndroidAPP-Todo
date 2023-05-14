package com.example.todoit.ui.login

import android.os.Bundle
import android.text.Editable
import androidx.activity.viewModels
import com.example.todoit.common.base.BaseActivity
import com.example.todoit.common.environment.Environment
import com.example.todoit.common.utils.toEditable
import com.example.todoit.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private var username: String = ""
    private var password: String = ""
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var activityLoginBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(activityLoginBinding.root)
        onClicks()
        if(Environment.isDebugging) {
            activityLoginBinding.username.text = toEditable(Environment.debugUserName)
            activityLoginBinding.password.text = toEditable(Environment.debugPassword)
            attemptLogin(
                username = activityLoginBinding.username.text.toString(),
                password = activityLoginBinding.password.text.toString()
            )
        }
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
        viewModel.attemptLogin(
            this@LoginActivity,
            username,
            password
        )
    }
}