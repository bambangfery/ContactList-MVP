package com.test.contactlist.di.module

import android.app.Activity
import com.test.contactlist.ui.contact.AddContactContract
import com.test.contactlist.ui.contact.AddContactPresenter
import com.test.contactlist.ui.main.MainContract
import com.test.contactlist.ui.main.MainPresenter
import com.test.contactlist.util.PrefManager
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {
    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter(PrefManager(activity))
    }

    @Provides
    fun addContactPresenter(): AddContactContract.Presenter {
        return AddContactPresenter()
    }

    @Provides
    fun getPreferences(): PrefManager {
        return PrefManager(activity)
    }

}