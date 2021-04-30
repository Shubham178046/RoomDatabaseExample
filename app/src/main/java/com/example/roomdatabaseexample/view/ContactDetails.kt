package com.example.roomdatabaseexample.view

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.adapter.ContactAdapter
import com.example.roomdatabaseexample.model.ContactModel
import com.example.roomdatabaseexample.viewmodel.ContactViewMOdel
import kotlinx.android.synthetic.main.activity_contact_details.*

class ContactDetails : AppCompatActivity() {
    var contactViewMOdel: ContactViewMOdel? = null
    var adapter: ContactAdapter? = null


    companion object {
        val PERMISSIONS_REQUEST_READ_CONTACTS = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)
        contactViewMOdel = ViewModelProvider(this).get(ContactViewMOdel::class.java)
        adapter = ContactAdapter()
        rvContact.layoutManager = LinearLayoutManager(this)
        rvContact.adapter = adapter
        loadContacts()
    }

    private fun loadContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS),
                PERMISSIONS_REQUEST_READ_CONTACTS
            )
        } else {
            // getContacts()
            contactViewMOdel!!.getContactData(this).observe(this, Observer {
                if (it != null) {
                    if (it.size > 0) {
                        adapter!!.setData(it)
                    }
                }
            })
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadContacts()
            } else {
                Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_LONG).show()
            }
        }
    }

    /* private fun getContacts() {
         val builder = StringBuilder()
         var contactModels = MutableLiveData<List<ContactModel>>()
         var contactModel: ArrayList<ContactModel> = ArrayList()
         val resolver: ContentResolver = contentResolver;
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
                     val cursorPhone = contentResolver.query(
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
                             contactViewMOdel!!.insertData(this, id, name, phoneNumValue)
                             Log.e("Name ===>", phoneNumValue);
                         }
                     }
                     cursorPhone.close()
                     contactModels.postValue(contactModel)
                 }
             }
         } else {
             Toast.makeText(this, "No contacts available!", Toast.LENGTH_LONG).show()
         }
         cursor.close()
     }*/
}