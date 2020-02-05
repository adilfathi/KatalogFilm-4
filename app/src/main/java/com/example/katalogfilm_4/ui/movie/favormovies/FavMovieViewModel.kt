package com.example.katalogfilm_4.ui.movie.favormovies

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.katalogfilm_4.database.MovieCatalogueDatabase
import com.example.katalogfilm_4.ui.movie.pojo.ResultsItem

class FavMovieViewModel(application: Application):AndroidViewModel(application) {
    private val favMovie = MutableLiveData<MutableList<ResultsItem>>()
    private val movieCatalogueDatabase: MovieCatalogueDatabase= MovieCatalogueDatabase.getDatabase(getApplication())

    val movies: MutableLiveData<MutableList<ResultsItem>>
        get() {
            return favMovie
        }
    fun fetchFunMovies(){
        AsyncTask.execute {
            favMovie.postValue(movieCatalogueDatabase.movieDao().getAllMovie())
        }
    }
}