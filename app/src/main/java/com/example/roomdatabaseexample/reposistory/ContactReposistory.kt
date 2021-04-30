package com.example.roomdatabaseexample.reposistory

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.roomdatabaseexample.model.ContactModel
import com.example.roomdatabaseexample.room.contectList.ContactDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactReposistory {
    companion object {
        var conatactDatabase: ContactDatabase? = null

        var conatactModel: LiveData<List<ContactModel>>? = null

        fun initializeDB(context: Context): ContactDatabase {
            return ContactDatabase.getContactClient(context)
        }

       /* fun insertData(
            context: Context,
            id : String,
            name : String,
            number : String
        ) {
            conatactDatabase = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val conatactDataModel = ContactModel(id, name, number)
                conatactDatabase!!.contactDao().InsertContactData(conatactDataModel)
            }
        }*/

        fun getContactData(context: Context): LiveData<List<ContactModel>> {
            conatactDatabase = initializeDB(context)
            conatactModel = conatactDatabase!!.contactDao().getContactData()
            return conatactModel!!
        }

        fun clearContactData(context: Context) {
            conatactDatabase = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                conatactDatabase!!.contactDao().clearContactData()
            }
        }
    }
}