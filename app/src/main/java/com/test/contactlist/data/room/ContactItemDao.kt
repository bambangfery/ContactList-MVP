package com.gobinda.mvp.sample.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.test.contactlist.data.models.ContactsItem
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Data access class Flight Event Model.
 * Insert, update, select, delete operation/query using RxJava so thread
 * scheduling, non block ui operation can be maintained easily.
 */
@Dao
interface ContactItemDao {

    @Query("SELECT * FROM contacts_item")
    fun loadContacts(): Observable<List<ContactsItem>>

    @Query("SELECT * from contacts_item ORDER BY " +
            "CASE WHEN :isAsc  = 1 THEN name END ASC," +
            "CASE WHEN :isAsc = 0 THEN name END DESC ")
    fun loadContactsByName(isAsc : Int): Observable<List<ContactsItem>>

    @Query("SELECT * from contacts_item ORDER BY " +
            "CASE WHEN :isAsc  = 1 THEN email END ASC," +
            "CASE WHEN :isAsc = 0 THEN email END DESC ")
    fun loadContactsByEmail(isAsc : Int): Observable<List<ContactsItem>>

    @Query("SELECT * from contacts_item ORDER BY " +
            "CASE WHEN :isAsc  = 1 THEN phoneNumber END ASC," +
            "CASE WHEN :isAsc = 0 THEN phoneNumber END DESC ")
    fun loadContactsByPhone(isAsc : Int): Observable<List<ContactsItem>>

    @Query("SELECT * from contacts_item ORDER BY " +
            "CASE WHEN :isAsc  = 1 THEN createdAt END ASC," +
            "CASE WHEN :isAsc = 0 THEN createdAt END DESC ")
    fun loadContactsByCreatedAt(isAsc : Int): Observable<List<ContactsItem>>

    @Insert(onConflict = REPLACE)
    fun insertContacts(contactsItem: ContactsItem)

    @Query("DELETE FROM contacts_item")
    fun deleteAllContacts()
}