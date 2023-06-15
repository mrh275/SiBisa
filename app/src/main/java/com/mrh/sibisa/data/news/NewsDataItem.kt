package com.mrh.sibisa.data.news

import com.google.gson.annotations.SerializedName

data class NewsDataItem(

	@field:SerializedName("news_description")
	val newsDescription: String,

	@field:SerializedName("updated_at")
	val updatedAt: Any,

	@field:SerializedName("created_at")
	val createdAt: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("news_title")
	val newsTitle: String,

	@field:SerializedName("news_excerpt")
	val newsExcerpt: String
)