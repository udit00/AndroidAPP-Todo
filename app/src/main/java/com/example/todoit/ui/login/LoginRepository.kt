package com.example.todoit.ui.login

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.example.todoit.common.base.BaseRepository
import javax.inject.Inject


class LoginRepository @Inject constructor(): BaseRepository() {

    private var loginData= MutableLiveData<String>()

    suspend fun attemptLogin(activity: Activity, username: String, password: String) {
        api.userLogin(
            userName = username,
            passWord = password
        )
    }
}