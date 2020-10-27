package com.test.contactlist.ui.main

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import com.gobinda.mvp.sample.room.ContactItemDao
import com.gobinda.mvp.sample.room.ContactItemDatabase
import com.test.contactlist.R
import com.test.contactlist.data.api.ApiServiceInterface
import com.test.contactlist.data.models.ContactsItem
import com.test.contactlist.di.component.DaggerActivityComponent
import com.test.contactlist.di.module.ActivityModule
import com.test.contactlist.util.PrefManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_sort.view.*
import javax.inject.Inject

class MainPresenter @Inject constructor (val pref: PrefManager):MainContract.Presenter, RadioGroup.OnCheckedChangeListener {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: MainContract.View
    private val ApiSevices: ApiServiceInterface = ApiServiceInterface.create()
    val compositeDisposable = CompositeDisposable()
    private var contactItemDao:ContactItemDao? = null
    var orderBy = 1
    var sortBy = 0
//    @Inject
//    lateinit var pref: PrefManager


    override fun getContact() {
        val subscribe = ApiSevices.getContact().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.onDomainSuccess(it.data.contacts)
            }, { error ->
                var msg = error.localizedMessage
                view.onDomainError(msg)
            })
        subscriptions.add(subscribe)
    }

    override fun getContactsItemDao(contactsItem: List<ContactsItem>, context: Context) {
        contactItemDao = ContactItemDatabase.getInstance(context).contactsItemDao()
        compositeDisposable.add(
            Observable.fromCallable{ contactItemDao?.deleteAllContacts() }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { Log.d("RxJava", "Delete Success") },
                    { Log.d("RxJava", "Delete Error") })
        )

        compositeDisposable.add(
            Observable.fromCallable{
                contactsItem.forEach {
                    contactItemDao?.insertContacts(it)
                }
            }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    contactItemDao?.loadContacts()
                        ?.subscribeOn(Schedulers.computation())
                        ?.observeOn(AndroidSchedulers.mainThread())
                        ?.subscribe({
                            view.setContactsItemDao(it)
                        }, { e -> Log.e("Message : ",e.toString()) })
                },{ e -> Log.e("Message : ",e.toString()) })
        )
    }

    override fun getDialogSort(item: View, dialog: AlertDialog) {
        item.sortby.setOnCheckedChangeListener(this)
        item.orderby.setOnCheckedChangeListener(this)
//        Log.d("sortby111 ",pref.getSortBy().toString())
        item.submit.setOnClickListener {
            submitSort()
            dialog.dismiss()
//            pref.setSortBy(sortBy)
        }
        sortChecked(item)
    }

    private fun sortChecked(item: View){
//        item.nameSort.isChecked = true
        when(sortBy){
            0 -> item.nameSort.isChecked = true
            1 -> item.emailSort.isChecked = true
            2 -> item.phoneSort.isChecked = true
            3 -> item.created_atSort.isChecked = true
        }

        when(orderBy){
            0 -> item.desc.isChecked = true
            1 -> item.asc.isChecked = true
        }
    }

    private fun submitSort(){
        when(sortBy){
            0 ->{
                contactItemDao?.loadContactsByName(orderBy)
                    ?.subscribeOn(Schedulers.computation())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe({
                        view.setContactsItemDao(it)
                    }, { e -> Log.e("Message : ",e.toString()) })
            }
            1 ->{
                contactItemDao?.loadContactsByEmail(orderBy)
                    ?.subscribeOn(Schedulers.computation())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe({
                        view.setContactsItemDao(it)
                    }, { e -> Log.e("Message : ",e.toString()) })
            }
            2 ->{
                contactItemDao?.loadContactsByPhone(orderBy)
                    ?.subscribeOn(Schedulers.computation())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe({
                        view.setContactsItemDao(it)
                    }, { e -> Log.e("Message : ",e.toString()) })
            }
            3 ->{
                contactItemDao?.loadContactsByCreatedAt(orderBy)
                    ?.subscribeOn(Schedulers.computation())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe({
                        view.setContactsItemDao(it)
                    }, { e -> Log.e("Message : ",e.toString()) })
            }
        }
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view = view
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        when(p1){
            R.id.nameSort -> {
                Log.d("SortBy  : ","Name")
                sortBy = 0
            }
            R.id.emailSort -> {
                Log.d("SortBy : ","Email")
                sortBy = 1
            }
            R.id.phoneSort -> {
                Log.d("SortBy : ","Phone")
                sortBy = 2
            }
            R.id.created_atSort -> {
                Log.d("SortBy : ","Created")
                sortBy = 3
            }
            R.id.asc -> {
                Log.d("OrderBy  : ","ASC")
                orderBy = 1
            }
            R.id.desc -> {
                Log.d("OrderBy : ","DESC")
                orderBy = 0
            }
        }
    }

}