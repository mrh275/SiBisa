package com.mrh.sibisa.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrh.sibisa.data.login.ResponseLogin
import com.mrh.sibisa.data.logout.ResponseLogout
import com.mrh.sibisa.data.register.DataRegister
import com.mrh.sibisa.data.register.ResponseRegister
import com.mrh.sibisa.network.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel : ViewModel() {

    private val loginResponse = MutableLiveData<ResponseLogin?>()
    private val logoutResponse = MutableLiveData<ResponseLogout?>()
    private val registerResponse = MutableLiveData<DataRegister?>()
    private val TAG = "AuthViewModel"

    fun login(email: String, password: String) {
        val client = ApiConfig.getApiService().login(email, password)
        client.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if(response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody != null) {
                        loginResponse.postValue(responseBody)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.e(TAG, "loginFailure: ${t.message}")
            }

        })
    }

    fun logout(token: String) {
        val client = ApiConfig.getApiService(token).logout()
        client.enqueue(object: Callback<ResponseLogout> {
            override fun onResponse(
                call: Call<ResponseLogout>,
                response: Response<ResponseLogout>
            ) {
                if(response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody != null) {
                        logoutResponse.postValue(responseBody)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseLogout>, t: Throwable) {
                Log.e(TAG, "loginFailure: ${t.message}")
            }

        })
    }

    fun register(nama: String, email: String, password: String) {
        val client = ApiConfig.getApiService().register(nama, email, password)
        client.enqueue(object : Callback<ResponseRegister> {
            override fun onResponse(
                call: Call<ResponseRegister>,
                response: Response<ResponseRegister>
            ) {
                if(response.isSuccessful) {
                    val responseBody = response.body()
                    registerResponse.postValue(responseBody?.data)
                }
            }

            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                Log.e(TAG, "loginFailure: ${t.message}")
            }

        })
    }

    fun getAuthData() : LiveData<ResponseLogin?> {
        return loginResponse
    }

    fun getLogoutResponse() : LiveData<ResponseLogout?> {
        return logoutResponse
    }
    fun getRegisterData() : LiveData<DataRegister?> {
        return registerResponse
    }
}