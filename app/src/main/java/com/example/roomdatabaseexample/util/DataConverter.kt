package com.example.roomdatabaseexample.util

import androidx.room.TypeConverter
import com.example.roomdatabaseexample.model.movie.ItemModel
import com.example.roomdatabaseexample.model.movie.MovieModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DataConverter {
    @TypeConverter
    fun fromList(optionValues: List<ItemModel>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<ItemModel>>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter
    fun toList(optionValuesString: String?): List<ItemModel>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<ItemModel>>() {}.type
        return gson.fromJson<List<ItemModel>>(optionValuesString, type)
    }
}