package com.example.roomdatabaseexample.room.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.*
import com.example.roomdatabaseexample.model.UserModel
import com.example.roomdatabaseexample.workmanager.UserDatabaseWorker
import java.util.concurrent.TimeUnit

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
                   /* .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val constraints = Constraints.Builder()
                                .setRequiresCharging(true)
                                .build()
                            val request = OneTimeWorkRequestBuilder<UserDatabaseWorker>().build()
                           *//* val request =PeriodicWorkRequestBuilder<UserDatabaseWorker>(24, TimeUnit.HOURS, 5,
                                TimeUnit.MINUTES)
                                .setConstraints(constraints)
                                .build()*//*
                        //    WorkManager.getInstance(context).enqueueUniquePeriodicWork("work",  ExistingPeriodicWorkPolicy.KEEP,request)
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    })*/
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCRE!!
            }
        }
    }
}