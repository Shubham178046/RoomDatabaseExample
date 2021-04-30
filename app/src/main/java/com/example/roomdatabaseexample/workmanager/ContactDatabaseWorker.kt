package com.example.roomdatabaseexample.workmanager

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.roomdatabaseexample.model.ContactModel
import com.example.roomdatabaseexample.reposistory.ContactReposistory
import com.example.roomdatabaseexample.room.contectList.ContactDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class ContactDatabaseWorker(context: Context, workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {
    var conatactDatabase: ContactDatabase? = ContactDatabase.getContactClient(applicationContext)
    override fun doWork(): Result {
        conatactDatabase!!.contactDao().clearContactData()
        CoroutineScope(Dispatchers.IO).launch {
            getContacts()
        }
        return Result.success()
    }
    private suspend fun getContacts() {
            val builder = StringBuilder()
            var contactModels = MutableLiveData<List<ContactModel>>()
            var contactModel: ArrayList<ContactModel> = ArrayList()
            val resolver: ContentResolver = applicationContext.contentResolver;
            val cursor = resolver.query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null,
                null
            )

            if (cursor!!.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    val phoneNumber = (cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
                    )).toInt()

                    if (phoneNumber > 0) {
                        val cursorPhone = applicationContext.contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                            arrayOf(id),
                            null
                        )

                        if (cursorPhone!!.count > 0) {
                            while (cursorPhone.moveToNext()) {
                                val phoneNumValue = cursorPhone.getString(
                                    cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                                )
                                builder.append("Contact: ").append(name).append(", Phone Number: ")
                                    .append(
                                        phoneNumValue
                                    ).append("\n\n")
                                conatactDatabase!!.contactDao()
                                    .InsertContactData(ContactModel(id, name, phoneNumValue))
                                Log.e("Name ===>", phoneNumValue);
                            }
                        }
                        cursorPhone.close()
                        contactModels.postValue(contactModel)
                    }
                }
            } else {
                CoroutineScope(Main).launch {
                    Toast.makeText(applicationContext, "No contacts available!", Toast.LENGTH_LONG)
                        .show()
                }

            }
            cursor.close()
        }
}