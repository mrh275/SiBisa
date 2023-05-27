package com.mrh.sibisa.data

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("loginData")
    val loginData: LoginData
)
