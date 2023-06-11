package com.example.todoit.common.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.todoit.common.utils.UTILS
import com.example.todoit.ui.login.LoginModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
open class BaseActivity @Inject constructor(): AppCompatActivity() {


    lateinit var mContext: Context
    lateinit var mActivity: Activity

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "User")
    }
    private val userPrefKey = stringPreferencesKey("User")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this@BaseActivity
    }

    fun successToast(message: String) {
        val toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }
    fun errorToast(message: String) {
        val toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

    fun saveUserToStorage(loginModel: LoginModel) {
        UTILS.savedLoginModel = loginModel
        val gson = Gson()
        val jsonLoginModel: String? = gson.toJson(loginModel)
        if(jsonLoginModel != null) {
            lifecycleScope.launch (Dispatchers.IO) {
                dataStore.edit { storedUser ->
                    storedUser[userPrefKey] = jsonLoginModel
                }
            }
        }
    }

    suspend fun getSavedUser(): LoginModel?{
        val gson = Gson()
        var savedUserJson: String?
        var loginModel: LoginModel? = null

        val pref =  dataStore.data.first()
        savedUserJson = pref[userPrefKey]
        if(savedUserJson != null) {
            loginModel = gson.fromJson(savedUserJson, LoginModel::class.java)
            if(loginModel!=null) {
                return loginModel
            }
        }
        return null
    }


}