package com.example.katalogfilm_4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.katalogfilm_4.ui.movie.MovieDao
import com.example.katalogfilm_4.ui.movie.pojo.ResultsItem
import com.example.katalogfilm_4.ui.tvshow.TvShowDao
import com.example.katalogfilm_4.ui.tvshow.pojo.ResultsItemTvShow

@Database (entities = [ResultsItem::class,ResultsItemTvShow::class],version = 1,exportSchema = false)
abstract class MovieCatalogueDatabase :RoomDatabase(){
    abstract fun movieDao():MovieDao
    abstract fun tvShowDao():TvShowDao


    companion object{
        const val DB_NAME="movie_catalogue_database"

        @Volatile
        private var INSTANCE : MovieCatalogueDatabase?=null

        fun getDatabase(context: Context):MovieCatalogueDatabase{
            if (INSTANCE==null){
                synchronized(MovieCatalogueDatabase::class.java){
                    if (INSTANCE==null){
                        INSTANCE= Room.databaseBuilder(context,MovieCatalogueDatabase::class.java,
                            DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}
