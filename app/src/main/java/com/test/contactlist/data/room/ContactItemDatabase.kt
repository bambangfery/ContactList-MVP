package com.gobinda.mvp.sample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.contactlist.data.models.ContactsItem

/**
 * ORM Database Helper class to connect to the database
 * named flight-sample.db and its records
 */
@Database(entities = arrayOf(ContactsItem::class), version = 1)
abstract class ContactItemDatabase : RoomDatabase() {

    abstract fun contactsItemDao(): ContactItemDao

    companion object {

        @Volatile
        private var INSTANCE: ContactItemDatabase? = null

        fun getInstance(context: Context): ContactItemDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: bindDatabase(context).also { INSTANCE = it }
            }

        private fun bindDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ContactItemDatabase::class.java,
                "contacts_item.db")
                .build()
    }
}
