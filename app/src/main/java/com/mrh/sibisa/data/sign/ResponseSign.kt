package com.mrh.sibisa.data.sign

import com.google.gson.annotations.SerializedName

data class ResponseSign(

	@field:SerializedName("lettersData")
	val lettersData: List<LettersDataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)