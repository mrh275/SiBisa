package com.mrh.sibisa.network.api

import com.mrh.sibisa.data.login.ResponseLogin
import com.mrh.sibisa.data.logout.ResponseLogout
import com.mrh.sibisa.data.news.ResponseNews
import com.mrh.sibisa.data.register.ResponseRegister
import com.mrh.sibisa.data.sign.ResponseSign
import com.mrh.sibisa.data.users.ResponseUserUpdate
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseLogin>

    @POST("logout")
    fun logout() : Call<ResponseLogout>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<ResponseRegister>

    @GET("news")
    fun getAllNews(): Call<ResponseNews>

    @FormUrlEncoded
    @POST("user/update")
    fun updateUser(
        @Field("name") name: String?,
        @Field("email") email: String?,
        @Field("password") password: String?
    ) : Call<ResponseUserUpdate>

    @GET("signs/letters")
    fun getLettersSign() : Call<ResponseSign>
}