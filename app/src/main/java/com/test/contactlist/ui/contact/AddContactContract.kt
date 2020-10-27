package com.test.contactlist.ui.contact

import com.test.contactlist.data.models.ContactsItem
import com.test.contactlist.ui.base.BaseContract

class AddContactContract {

    interface View: BaseContract.View{

        fun onDomainSuccess(contactsItem: List<ContactsItem>)
        fun onDomainError(msg: String)

    }

    interface Presenter: BaseContract.Presenter<AddContactContract.View>{

    }

}