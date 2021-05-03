package com.example.roomdatabaseexample.reposistory

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.roomdatabaseexample.model.ContactModel
import com.example.roomdatabaseexample.model.movie.MovieModel
import com.example.roomdatabaseexample.network.RetrofitService
import com.example.roomdatabaseexample.room.movie.MovieDatabase
import com.example.roomdatabaseexample.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieReposistory : ViewModel() {

    fun getMoviesData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = getMoviesDataApi()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, msg = e.message ?: "Error Occurred! in Login"))
        }
    }

    private suspend fun getMoviesDataApi(): MovieModel {
        return withContext(Dispatchers.IO) {
            RetrofitService.getService().getMoviesData()
        }
    }

    companion object {
        var movieDatabase: MovieDatabase? = null

        var movieModel: LiveData<MovieModel>? = null

        fun initializeDB(context: Context): MovieDatabase {
            return MovieDatabase.getMovieClient(context)
        }

         fun insertData(
             context: Context,
             movieModel: MovieModel
         ) {
             movieDatabase = initializeDB(context)
             CoroutineScope(Dispatchers.IO).launch {
                 movieDatabase!!.movieDao().InsertMovieData(movieModel)
             }
         }

        fun getMovieData(context: Context): LiveData<MovieModel> {
            movieDatabase = initializeDB(context)
            movieModel = movieDatabase!!.movieDao().getMovieData()
            return movieModel!!
        }

        fun clearMovieData(context: Context) {
            movieDatabase = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                movieDatabase!!.movieDao().clearMovieData()
            }
        }
    }
}