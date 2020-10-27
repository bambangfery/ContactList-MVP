package com.test.contactlist.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "contacts_item")
data class ContactsItem(
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	@Transient
	val id : Int,

	@ColumnInfo(name = "createdAt")
	@field:SerializedName("createdAt")
	val createdAt: Long,

	@ColumnInfo(name = "phoneNumber")
	@field:SerializedName("phoneNumber")
	val phoneNumber: Long,

	@ColumnInfo(name = "name")
	@field:SerializedName("name")
	val name: String,

	@ColumnInfo(name = "email")
	@field:SerializedName("email")
	val email: String
)