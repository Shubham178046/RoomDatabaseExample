package com.example.roomdatabaseexample.workmanager

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.roomdatabaseexample.model.UserModel
import com.example.roomdatabaseexample.reposistory.UserReposistory
import com.example.roomdatabaseexample.room.user.UserDatabase
import com.example.roomdatabaseexample.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDatabaseWorker(context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
) {
    var userDatabase: UserDatabase? = null
    private val TAG = UserDatabaseWorker::class.java.simpleName
    override fun doWork(): Result {
        userDatabase = UserDatabase.getUserClient(applicationContext)
        userDatabase!!.userDao().clearUserData()
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0 until 4000) {
                userDatabase!!.userDao().InsertUserData(UserModel("User$i", "Data$i", "Address$i"))
            }
        }
        return Result.success()
    }
}