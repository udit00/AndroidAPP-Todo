package com.example.todoit.ui.login

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoit.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: LoginRepository) : BaseViewModel(){
    val loginData: LiveData<LoginModel>
    get() = repo.loginResponse

    init {
        isError = repo.isError
    }
    fun attemptLogin(activity: Activity, username: String, password: String) {
        viewModelScope.launch (Dispatchers.IO){
            repo.attemptLogin(activity, username, password)
        }
    }

}