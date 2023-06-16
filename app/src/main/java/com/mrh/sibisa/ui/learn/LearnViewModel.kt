package com.mrh.sibisa.ui.learn

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrh.sibisa.data.sign.LettersDataItem
import com.mrh.sibisa.data.sign.ResponseSign
import com.mrh.sibisa.network.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LearnViewModel: ViewModel() {

    val listLetters = MutableLiveData<List<LettersDataItem?>?>()

    fun setLettersSign(token: String) {
        val client = ApiConfig.getApiService(token).getLettersSign()
        client.enqueue(object : Callback<ResponseSign> {
            override fun onResponse(call: Call<ResponseSign>, response: Response<ResponseSign>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    listLetters.postValue(responseBody?.lettersData)
                }
            }

            override fun onFailure(call: Call<ResponseSign>, t: Throwable) {
                Log.e("MainViewModel", "onFailure : ${t.message}")
            }
        })
    }

    fun getLetterSign(): LiveData<List<LettersDataItem?>?> {
        return listLetters
    }
}