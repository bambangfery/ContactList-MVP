package com.test.contactlist.data.models

import com.google.gson.annotations.SerializedName

data class Contact(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("messages")
	val messages: List<String>,

	@field:SerializedName("status")
	val status: String
)