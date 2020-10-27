package com.test.contactlist.ui.main.adapter

import com.test.contactlist.R
import com.test.contactlist.data.models.ContactsItem
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.contacts_item_list.view.*
import java.text.SimpleDateFormat
import java.util.*

class ContactsItemAdapter(val data : List<ContactsItem>) : Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.contacts_item_list
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        val itemView = viewHolder.itemView
        itemView.name.text = ": "+data[position].name
        itemView.email.text = ": "+data[position].email
        itemView.phone.text = ": "+data[position].phoneNumber.toString()
        try {
            val sdf = SimpleDateFormat("EEE, d MMM yyyy")
            val netDate = Date(data[position].createdAt)
            itemView.created_at.text = ": "+sdf.format(netDate)
        } catch (e: Exception) {
             e.toString()
        }
    }
}