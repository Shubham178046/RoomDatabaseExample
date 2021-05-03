package com.example.roomdatabaseexample.model.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.roomdatabaseexample.util.DataConverter
import java.util.ArrayList

@Entity(tableName = "Movie")
open class MovieModel {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = ""
    @ColumnInfo(name = "createdby")
    var created_by: String = ""
    @ColumnInfo(name = "description")
    var description: String = ""
    @ColumnInfo(name = "iso")
    var iso_639_1: String? = ""
    @ColumnInfo(name = "name")
    var name: String = ""
    var item_count: Int = 0
    var favorite_count: Int = 0
    @TypeConverters(DataConverter::class)
    @ColumnInfo(name = "items")
    var items: List<ItemModel> = ArrayList<ItemModel>()
}