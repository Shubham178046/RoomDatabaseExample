package com.example.roomdatabaseexample.room.contectList

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdatabaseexample.model.ContactModel
import com.example.roomdatabaseexample.model.UserModel

@Dao
interface ContactDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertContactData(contactModel: ContactModel)

    @Query("SELECT * FROM Contact")
    fun getContactData(): LiveData<List<ContactModel>>

    @Query("DELETE FROM Contact")
    fun clearContactData()
}