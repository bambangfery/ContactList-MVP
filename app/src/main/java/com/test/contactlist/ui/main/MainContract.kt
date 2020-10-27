package com.test.contactlist.ui.main

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.test.contactlist.data.models.ContactsItem
import com.test.contactlist.ui.base.BaseContract


class MainContract {

    interface View: BaseContract.View{

        fun onDomainSuccess(contactsItem: List<ContactsItem>)
        fun onDomainError(msg: String)
        fun setContactsItemDao(contactsItem: List<ContactsItem>)

    }

    interface Presenter:BaseContract.Presenter<View>{
        fun getContact()
        fun getContactsItemDao(contactsItem: List<ContactsItem>, context: Context)
        fun getDialogSort(view: android.view.View, mAlertDialog: AlertDialog)
    }
}