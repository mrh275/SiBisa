package com.mrh.sibisa.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrh.sibisa.data.sign.ResponseSign
import com.mrh.sibisa.data.sign.SignItem
import com.mrh.sibisa.network.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val listSigns = MutableLiveData<List<SignItem?>?>()

    fun setSigns() {
        val client = ApiConfig.getApiService().getAllSigns()
        client.enqueue(object : Callback<ResponseSign> {
            override fun onResponse(call: Call<ResponseSign>, response: Response<ResponseSign>) {
                if(response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody != null) {
                        listSigns.postValue(responseBody.data)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseSign>, t: Throwable) {
                Log.e("MainViewModel", "onFailure : ${t.message}")
            }

        })
    }

    fun getSigns(): LiveData<List<SignItem?>?> {
        return listSigns
    }
}