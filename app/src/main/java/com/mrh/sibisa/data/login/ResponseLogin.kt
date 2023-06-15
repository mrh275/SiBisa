package com.mrh.sibisa.data.login

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("userData")
	val userData: UserData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("accessToken")
	val accessToken: String,

	@field:SerializedName("status")
	val status: Int
)