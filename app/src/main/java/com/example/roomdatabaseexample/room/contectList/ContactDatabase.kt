package com.example.roomdatabaseexample.room.contectList

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.roomdatabaseexample.model.ContactModel
import com.example.roomdatabaseexample.model.UserModel
import com.example.roomdatabaseexample.room.user.UserDAO
import com.example.roomdatabaseexample.workmanager.ContactDatabaseWorker

@Database(entities = arrayOf(ContactModel::class), version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDAO

    companion object {
        @Volatile
        private var INSTANCRE: ContactDatabase? = null

        fun getContactClient(context: Context): ContactDatabase {
            if (INSTANCRE != null) return INSTANCRE!!

            synchronized(this)
            {
                INSTANCRE = Room
                    .databaseBuilder(context, ContactDatabase::class.java, "CONTACT_DATABASE")
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<ContactDatabaseWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCRE!!
            }
        }
    }

}