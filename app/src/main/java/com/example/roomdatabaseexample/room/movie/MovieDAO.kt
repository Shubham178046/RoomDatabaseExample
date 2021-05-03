package com.example.roomdatabaseexample.room.movie

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdatabaseexample.model.movie.MovieModel
@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertMovieData(movieModel: MovieModel)

    @Query("SELECT * FROM Movie")
    fun getMovieData(): LiveData<MovieModel>

    @Query("DELETE FROM Movie")
    fun clearMovieData()
}