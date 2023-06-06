package com.example.todoit.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.todoit.common.base.BaseActivity
import com.example.todoit.common.environment.Environment
import com.example.todoit.common.utils.logger
import com.example.todoit.common.utils.toEditable
import com.example.todoit.databinding.ActivityLoginBinding
import com.example.todoit.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private var username: String = ""
    private var password: String = ""
    private lateinit var savedUserModel: LoginModel
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var activityLoginBinding: ActivityLoginBinding
    private val isDebugging = MutableLiveData<Boolean>()
    private val setUserCredsTextView = MutableLiveData<LoginModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        activityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(activityLoginBinding.root)
        setOnClicks()
        setObservers()

        lifecycleScope.launch(Dispatchers.IO) {
            val loginModel = getSavedUser()
            if (loginModel != null) {
                savedUserModel = loginModel
                setUserCredsTextView.postValue(savedUserModel)
                attemptLogin(
                    savedUserModel.username,
                    savedUserModel.password
                )
            } else {
                if(Environment.isDebugging) {
                    isDebugging.postValue(true)
                }
            }
        }

    }

    private fun setObservers() {
        setUserCredsTextView.observe(this) { creds ->
            if(creds != null) {
                activityLoginBinding.username.text = toEditable(creds.username)
                activityLoginBinding.password.text = toEditable(creds.password)
            }
        }

        isDebugging.observe(this) { isDebugging ->
            if(isDebugging) {
                activityLoginBinding.username.text = toEditable(Environment.debugUserName)
                activityLoginBinding.password.text = toEditable(Environment.debugPassword)
                attemptLogin(
                    username = activityLoginBinding.username.text.toString(),
                    password = activityLoginBinding.password.text.toString()
                )
            }
        }

        viewModel.isError.observe(this) { errorMessage ->
            if(errorMessage != null) {
                errorToast(errorMessage)
            }
        }

        viewModel.loginData.observe(this) { result ->
            if (result != null) {
                saveUserToStorage(result)
                successToast(result.message)
                goToHomePage()
            }
        }

    }

    private fun goToHomePage() {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun setOnClicks(){
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
            activity = this@LoginActivity,
            username = username,
            password = password,
            appVersion = Environment.appVersion
        )
    }
}