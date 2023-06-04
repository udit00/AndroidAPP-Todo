package com.example.todoit.common.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.todoit.R
import com.example.todoit.common.environment.CommonResponse
import com.example.todoit.ui.login.LoginModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity @Inject constructor(): AppCompatActivity() {


    lateinit var mContext: Context
    lateinit var mActivity: Activity
    private val userLoginModelMutData = MutableLiveData<LoginModel>()
    val userLoginModelLiveData: LiveData<LoginModel>
    get() = userLoginModelMutData
    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "User")
    }
    private val userPrefKey = stringPreferencesKey("User")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this@BaseActivity
    }

    fun successToast(message: String) {
        val toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG).show()
    }
    fun errorToast(message: String) {
        val toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG).show()
    }

    suspend fun saveUserToStorage(loginModel: LoginModel) {
        val gson = Gson()
        val jsonLoginModel: String? = gson.toJson(loginModel)
        if(jsonLoginModel != null) {
            this.dataStore.edit { storedUser ->
                storedUser[userPrefKey] = jsonLoginModel
            }
        }
    }

    fun getSavedUser(){
        val gson = Gson()
        lifecycleScope.launch {
            val pref = dataStore.data.first()
            val savedUserJson = pref[userPrefKey]
            if(savedUserJson != null) {
                val loginModel = gson.fromJson(savedUserJson, LoginModel::class.java)
                if(loginModel!=null) {
                    userLoginModelMutData.postValue(loginModel)
                }
            }
        }
    }


}