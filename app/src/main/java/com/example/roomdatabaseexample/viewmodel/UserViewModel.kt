package com.example.roomdatabaseexample.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.roomdatabaseexample.model.UserModel
import com.example.roomdatabaseexample.reposistory.UserReposistory

class UserViewModel : ViewModel() {

    /*fun insertData(context: Context, username: String, userSurename: String, userAddress: String) {
        UserReposistory.insertData(context, username, userSurename, userAddress)
    }*/
    /* fun getUserData(context: Context): LiveData<List<UserModel>> {
         return UserReposistory.getUserData(context)
     } */
    fun getUserData(context: Context): LiveData<PagedList<UserModel>> {
        val factory: DataSource.Factory<Int, UserModel> =
            UserReposistory.getUserData(context)
        val pagedListBuilder: LivePagedListBuilder<Int, UserModel> =
            LivePagedListBuilder<Int, UserModel>(
                factory,
                10
            )
        return pagedListBuilder.build()
    }

    fun clearUserData(context: Context) {
        UserReposistory.clearUserData(context)
    }
}