package com.test.contactlist.ui.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.contactlist.R
import com.test.contactlist.data.models.ContactsItem
import javax.inject.Inject

class AddContactActivity : AppCompatActivity(), AddContactContract.View {

    @Inject
    lateinit var presenter: AddContactContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
    }

    override fun onDomainSuccess(contactsItem: List<ContactsItem>) {
        TODO("Not yet implemented")
    }

    override fun onDomainError(msg: String) {
        TODO("Not yet implemented")
    }

}