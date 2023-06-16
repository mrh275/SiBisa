package com.mrh.sibisa.data.sign

import com.google.gson.annotations.SerializedName

data class LettersDataItem(

	@field:SerializedName("updated_at")
	val updatedAt: Any? = null,

	@field:SerializedName("sign")
	val sign: String? = null,

	@field:SerializedName("created_at")
	val createdAt: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("category")
	val category: String? = null
)