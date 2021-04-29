package com.example.roomdatabaseexample.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contact")
data class ContactModel(
    @PrimaryKey
    @ColumnInfo(name = "contactId")
    var contactId : String,
    @ColumnInfo(name = "contactName")
    var contactName : String,
    @ColumnInfo(name = "contactNumber")
    var contactNumber : String
)