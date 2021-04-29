package com.example.roomdatabaseexample.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserModel(
    @ColumnInfo(name = "userName")
    var Username: String,
    @ColumnInfo(name = "userSurname")
    var UserSurename: String,
    @ColumnInfo(name = "userAddress")
    var UserAddres: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    var UserId: Int? = null
}
