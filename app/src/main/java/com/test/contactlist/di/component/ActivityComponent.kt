package com.test.contactlist.di.component

import com.test.contactlist.di.module.ActivityModule
import com.test.contactlist.ui.contact.AddContactActivity
import com.test.contactlist.ui.main.MainActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun injectMain(mainActivity: MainActivity)
    fun injectAddContact(addContactActivity: AddContactActivity)

}