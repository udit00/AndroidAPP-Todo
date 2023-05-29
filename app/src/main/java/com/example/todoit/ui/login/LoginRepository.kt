package com.example.todoit.ui.login

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoit.common.base.BaseRepository
import com.example.todoit.common.environment.CommonResponse
import com.example.todoit.common.utils.isResponseOk
import com.example.todoit.common.utils.logger
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import java.net.SocketException
import javax.inject.Inject


class LoginRepository @Inject constructor(): BaseRepository() {

    private var loginData= MutableLiveData<LoginModel>()

    val loginResponse: LiveData<LoginModel> = loginData


    suspend fun attemptLogin(activity: Activity, username: String, password: String) {
        try {
            val response: Response<CommonResponse> = api.userLogin(
                userName = username,
                passWord = password
            )
            if (isResponseOk(response)) {
                val gson = Gson()
                var resultStr = getResult0(response)
                response.body()?.data?.let {

                    val loginResult = object : TypeToken<List<LoginModel?>?>() {}.type
                    val loginList: ArrayList<LoginModel> = gson.fromJson(resultStr, loginResult);
                    if(loginList.size > 0) {
                        val loginResponse : LoginModel = loginList[0]
                        loginData.postValue(loginResponse)
                    }

                }
                if(response.body()?.data == null) {
                    isError.postValue(response.body()?.message)
                }
            }
        } catch (ex: SocketException) {
//            isError.postValue(ex.message);
            logger(
                activity = activity,
                msg =  ex.message.toString()
            )
            isError.postValue(ERR_NETWORK);
        } catch (ex: Exception) {
            isError.postValue(ex.message)
        }
    }
}