package com.mrh.sibisa.network.api

import com.mrh.sibisa.data.ResponseLogin
import com.mrh.sibisa.data.sign.ResponseSign
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): ResponseLogin

    @GET("signs")
    fun getAllSigns(): Call<ResponseSign>
}