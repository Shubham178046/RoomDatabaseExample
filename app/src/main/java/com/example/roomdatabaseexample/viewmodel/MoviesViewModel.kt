package com.example.roomdatabaseexample.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.roomdatabaseexample.model.ContactModel
import com.example.roomdatabaseexample.model.movie.MovieModel
import com.example.roomdatabaseexample.reposistory.ContactReposistory
import com.example.roomdatabaseexample.reposistory.MovieReposistory
import com.example.roomdatabaseexample.util.Resource

class MoviesViewModel : ViewModel() {

    fun getMovieData(): LiveData<Resource<MovieModel>>? {
        return MovieReposistory().getMoviesData()
    }
 fun insertData(context: Context, movieModel: MovieModel) {
     MovieReposistory.insertData(context,movieModel)
    }

    fun getMovieData(context: Context): LiveData<MovieModel> {
        return MovieReposistory.getMovieData(context)
    }

    fun clearMovieData(context: Context) {
        ContactReposistory.clearContactData(context)
    }
}