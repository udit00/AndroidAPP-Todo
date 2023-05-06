package com.example.todoit.ui.Login

import android.app.Activity
import androidx.lifecycle.viewModelScope
import com.example.todoit.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: LoginRepository) : BaseViewModel(){

    fun attemptLogin(activity: Activity, username: String, password: String) {
        viewModelScope.launch (Dispatchers.IO){
            repo.attemptLogin(activity, username, password)
        }
    }

}