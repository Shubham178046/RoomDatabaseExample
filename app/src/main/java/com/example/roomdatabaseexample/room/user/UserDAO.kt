package com.example.roomdatabaseexample.room.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdatabaseexample.model.UserModel

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertUserData(userModel: UserModel)

    @Query("SELECT * FROM User")
    fun getUserData(): LiveData<List<UserModel>>

    @Query("DELETE FROM User")
    fun clearUserData()
}