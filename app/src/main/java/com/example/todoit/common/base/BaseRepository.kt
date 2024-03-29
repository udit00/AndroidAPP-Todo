package com.example.todoit.common.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todoit.API
import com.example.todoit.common.environment.CommonResponse
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


open class BaseRepository @Inject constructor() {
    val ERR_PARSING_JSON = "Error While Parsing the JSON."
    val ERR_NETWORK = "Network error. Please try again."
    val ERR_API = "Error during communication with the server. Please try again after some time."
    protected var isErrorData= MutableLiveData<String>()
    val isError: LiveData<String> = isErrorData
    var isSuccess = MutableLiveData<String>()
    protected var isLoadingMutDat = MutableLiveData<Boolean>()
    val isLoadingLiveData = isLoadingMutDat
    @Inject
    lateinit var api: API


    fun getResult0(response: Response<CommonResponse>): String {
        if(response.body()?.data != null) {
            var obj: JSONObject? = null
            try {
                obj = JSONObject(response.body()?.data.toString())
                return obj.getJSONArray("result_0").toString()
            }
            catch (e: JSONException) {
//                logger(e.toString())
                isErrorData.postValue(e.message);
                return "";
            }
        }
        isErrorData.postValue(ERR_PARSING_JSON)
        return "";
    }

}