package com.example.roomdatabaseexample.reposistory

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.roomdatabaseexample.model.UserModel
import com.example.roomdatabaseexample.room.user.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserReposistory {
    companion object {
        var userDatabase: UserDatabase? = null

        var userModel: LiveData<List<UserModel>>? = null

        fun initializeDB(context: Context): UserDatabase {
            return UserDatabase.getUserClient(context)
        }

        fun insertData(
            context: Context,
            username: String,
            userSurname: String,
            userAddress: String
        ) {
            userDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                val userDataModel = UserModel(username, userSurname, userAddress)
                userDatabase!!.userDao().InsertUserData(userDataModel)
            }
        }

        fun getUserData(context: Context): LiveData<List<UserModel>> {
            userDatabase = initializeDB(context)
            userModel = userDatabase!!.userDao().getUserData()
            return userModel!!
        }

        fun clearUserData(context: Context) {
            userDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                userDatabase!!.userDao().clearUserData()
            }
        }
    }
}