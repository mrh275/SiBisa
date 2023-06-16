package com.mrh.sibisa.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrh.sibisa.data.news.NewsDataItem
import com.mrh.sibisa.data.news.ResponseNews
import com.mrh.sibisa.data.users.ResponseUserUpdate
import com.mrh.sibisa.network.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val listNews = MutableLiveData<List<NewsDataItem>>()
    val updateUser = MutableLiveData<ResponseUserUpdate?>()


    fun setNews() {
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

    fun updateUser(name: String?, email: String?, password: String?, token: String) {
        val client = ApiConfig.getApiService(token).updateUser(name, email, password)
        client.enqueue(object : Callback<ResponseUserUpdate> {
            override fun onResponse(
                call: Call<ResponseUserUpdate>,
                response: Response<ResponseUserUpdate>
            ) {
                if(response.isSuccessful) {
                    val responseBody = response.body()
                    updateUser.postValue(responseBody)
                }
            }

            override fun onFailure(call: Call<ResponseUserUpdate>, t: Throwable) {
                Log.e("MainViewModel", "onFailure : ${t.message}")
            }

        })
    }

    fun getNews(): LiveData<List<NewsDataItem>> {
        return listNews
    }

    fun getUserUpdateResponse(): LiveData<ResponseUserUpdate?> {
        return updateUser
    }
}