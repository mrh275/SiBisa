package com.mrh.sibisa.data.news

import com.google.gson.annotations.SerializedName

data class ResponseNews(

	@field:SerializedName("newsData")
	val newsData: List<NewsDataItem>,

	@field:SerializedName("messages")
	val messages: String,

	@field:SerializedName("status")
	val status: Int
)