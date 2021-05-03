package com.example.roomdatabaseexample.room.movie

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.roomdatabaseexample.model.ContactModel
import com.example.roomdatabaseexample.model.movie.MovieModel
import com.example.roomdatabaseexample.room.contectList.ContactDAO
import com.example.roomdatabaseexample.util.DataConverter

@Database(entities = arrayOf(MovieModel::class), version = 1, exportSchema = false)
@TypeConverters(
    DataConverter::class
)
abstract class MovieDatabase  : RoomDatabase(){
    abstract fun movieDao(): MovieDAO
    companion object {
        @Volatile
        private var INSTANCRE: MovieDatabase? = null

        fun getMovieClient(context: Context): MovieDatabase {
            if (INSTANCRE != null) return INSTANCRE!!

            synchronized(this)
            {
                INSTANCRE = Room
                    .databaseBuilder(context, MovieDatabase::class.java, "MOVIE_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCRE!!
            }
        }
    }
}