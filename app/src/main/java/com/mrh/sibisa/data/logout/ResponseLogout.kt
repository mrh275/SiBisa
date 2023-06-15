package com.mrh.sibisa.data.logout

import com.google.gson.annotations.SerializedName

data class ResponseLogout(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)
