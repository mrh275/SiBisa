package com.mrh.sibisa.data

import com.google.gson.annotations.SerializedName

data class LoginData(

    @field:SerializedName("userId")
    val userId: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("token")
    val token: String
)
