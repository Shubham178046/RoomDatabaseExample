package com.example.roomdatabaseexample.model.movie

import java.io.Serializable

open class ItemModel : Serializable {
    var adult: Boolean? = null
    var backdrop_path: String? = ""
    var media_type: String? = ""
    var original_language: String? = ""
    var original_title: String? = ""
    var overview: String? = ""
    var popularity: Double? = 0.0
    var poster_path: String? = ""
    var release_date: String? = ""
    var title: String? = ""
    var video: Boolean? = null
    var vote_average: Double? = 0.0
    var vote_count: Int? = 0
    var genre_ids: List<Int>? = null
    var id : Int?=0
}