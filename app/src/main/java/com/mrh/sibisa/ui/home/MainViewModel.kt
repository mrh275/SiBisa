package com.mrh.sibisa.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrh.sibisa.data.news.NewsDataItem
import com.mrh.sibisa.data.news.ResponseNews
import com.mrh.sibisa.network.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val listNews = MutableLiveData<List<NewsDataItem>>()

    fun setSigns() {
        val client = ApiConfig.getApiService().getAllNews()
        client.enqueue(object : Callback<ResponseNews> {
            override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    listNews.postValue(responseBody?.newsData)
                }
            }

            override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                Log.e("MainViewModel", "onFailure : ${t.message}")
            }
        })
    }

    fun getNews(): LiveData<List<NewsDataItem>> {
        return listNews
    }
}