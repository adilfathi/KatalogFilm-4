package com.example.katalogfilm_4.ui.tvshow.favtvshow

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.katalogfilm_4.MovieCatalogue
import com.example.katalogfilm_4.database.MovieCatalogueDatabase
import com.example.katalogfilm_4.ui.movie.pojo.ResultsItem
import com.example.katalogfilm_4.ui.tvshow.pojo.ResultsItemTvShow

class FavTVShowViewModel(application: Application): AndroidViewModel(application) {
    private val  favTVShow = MutableLiveData<MutableList<ResultsItemTvShow>>()
    private  val movieCatalogueDatabase : MovieCatalogueDatabase= MovieCatalogueDatabase.getDatabase(getApplication())

    internal val getTvShowList: MutableLiveData<MutableList<ResultsItemTvShow>>
    get() {
        return favTVShow
    }
    internal fun fetchFavTvShow(){
        AsyncTask.execute {
            favTVShow.postValue(movieCatalogueDatabase.tvShowDao().getAllTvShow())
        }
    }

}