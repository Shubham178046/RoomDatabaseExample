package com.example.roomdatabaseexample.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.roomdatabaseexample.model.ContactModel
import com.example.roomdatabaseexample.model.UserModel
import com.example.roomdatabaseexample.reposistory.ContactReposistory
import com.example.roomdatabaseexample.reposistory.UserReposistory

class ContactViewMOdel : ViewModel() {
    fun insertData(context: Context, id: String, name: String, number: String) {
        ContactReposistory.insertData(context, id, name, number)
    }

    fun getContactData(context: Context): LiveData<List<ContactModel>> {
        return ContactReposistory.getContactData(context)
    }

    fun clearContactData(context: Context) {
        ContactReposistory.clearContactData(context)
    }
}