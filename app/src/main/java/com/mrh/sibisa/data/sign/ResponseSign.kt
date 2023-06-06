package com.mrh.sibisa.data.sign

import com.google.gson.annotations.SerializedName

data class ResponseSign(

    @field:SerializedName("data")
	val data: List<SignItem?>? = null,

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("status")
	val status: String? = null
)