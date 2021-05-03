package com.example.roomdatabaseexample.network

import com.example.roomdatabaseexample.model.movie.MovieModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("3/list/1?api_key=cddae2e178049deef0e0434c22f9ce53&language=en-US")
    suspend fun getMoviesData(): MovieModel
}