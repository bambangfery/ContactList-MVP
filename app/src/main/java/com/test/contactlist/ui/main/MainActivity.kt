package com.test.contactlist.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.test.contactlist.R
import com.test.contactlist.data.models.ContactsItem
import com.test.contactlist.di.component.DaggerActivityComponent
import com.test.contactlist.di.module.ActivityModule
import com.test.contactlist.ui.contact.AddContactActivity
import com.test.contactlist.ui.main.adapter.ContactsItemAdapter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() , MainContract.View{

    @Inject
    lateinit var presenter: MainContract.Presenter

    val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()
        presenter.attach(this)
        presenter.getContact()

        add_contact.setOnClickListener {
            val addComtactIntent = Intent(this, AddContactActivity::class.java)
            startActivity(addComtactIntent)
        }

        sort.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_sort, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            val  mAlertDialog = mBuilder.show()
            mAlertDialog.window?.setLayout(650, ViewGroup.LayoutParams.WRAP_CONTENT)

            presenter.getDialogSort(mDialogView, mAlertDialog)
        }

    }

    override fun onDomainSuccess(contactsItem: List<ContactsItem>) {
        presenter.getContactsItemDao(contactsItem, this)
    }

    override fun onDomainError(msg: String) {

    }

    override fun setContactsItemDao(contactsItem: List<ContactsItem>) {
        adapter.apply {
            clear()
            notifyDataSetChanged()
        }
        contactsItem.forEach {
            adapter.add(ContactsItemAdapter(contactsItem))
        }
        rv_list_contact.adapter = adapter
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.injectMain(this)
    }
}