package com.example.roomdatabaseexample

import android.app.Application
import com.example.roomdatabaseexample.model.UserModel
import com.example.roomdatabaseexample.reposistory.UserReposistory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            UserReposistory.clearUserData(this@RoomApplication)
            for (i in 0 until 4000) {
                UserReposistory.insertData(this@RoomApplication, "User$i", "Data$i", "Address$i")
            }
        }
    }
}