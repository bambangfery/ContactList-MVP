package com.test.contactlist.util

import android.content.Context
import android.content.SharedPreferences

class PrefManager (val context: Context) {
    private val PREFS_NAME = "kotlincodes"
    private val  SORT_BY = "sortBy"
    private val  ORDER_BY = "orderBy"
    val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setSortBy(sort: Int){
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(SORT_BY, sort)
        editor.commit()
    }

    fun getSortBy(): Int {
        return sharedPref.getInt(SORT_BY, 1)
    }

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.commit()
    }


}