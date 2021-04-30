package com.example.roomdatabaseexample.room.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.roomdatabaseexample.model.UserModel
import com.example.roomdatabaseexample.workmanager.UserDatabaseWorker

@Database(entities = arrayOf(UserModel::class), version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        @Volatile
        private var INSTANCRE: UserDatabase? = null

        fun getUserClient(context: Context): UserDatabase {
            if (INSTANCRE != null) return INSTANCRE!!

            synchronized(this)
            {
                INSTANCRE = Room
                    .databaseBuilder(context, UserDatabase::class.java, "USER_DATABASE")
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<UserDatabaseWorker>().build()
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