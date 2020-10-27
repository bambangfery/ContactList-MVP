package com.test.contactlist.data.models

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("contacts")
	val contacts: List<ContactsItem>
)