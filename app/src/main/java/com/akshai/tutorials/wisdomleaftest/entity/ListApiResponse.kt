package com.akshai.tutorials.wisdomleaftest.entity

import com.google.gson.annotations.SerializedName


data class ListApiResponse(

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("download_url")
	val downloadUrl: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)
