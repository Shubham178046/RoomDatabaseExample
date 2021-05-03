package com.example.roomdatabaseexample.model.movie

import java.util.ArrayList

open class CategoryModel {
    var categoryID: Int? = null
    var IMDB: Int? = null
    var categoryTitle: String? = ""
    var movieList: ArrayList<ItemModel>? = null
}