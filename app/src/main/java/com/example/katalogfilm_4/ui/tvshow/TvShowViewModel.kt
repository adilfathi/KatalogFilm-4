package com.example.katalogfilm_4.ui.tvshow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.katalogfilm_4.MovieCatalogue
import com.example.katalogfilm_4.ui.tvshow.pojo.ResponseTVShow

class TvShowViewModel(application: Application):AndroidViewModel(application) {
    private val responseTVShow = MutableLiveData<ResponseTVShow>()

    internal val getTvShowList: MutableLiveData<ResponseTVShow>
        get() {
            return responseTVShow
        }
    internal fun doRequestListTvShows(){
        AndroidNetworking.get("https://api.themoviedb.org/3/discover/tv")
            .addQueryParameter("api_key",MovieCatalogue.MOVIE_DB_KEY)
            .addQueryParameter("language",(getApplication()as MovieCatalogue).getLanguage())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsObject(ResponseTVShow::class.java,object :ParsedRequestListener<ResponseTVShow>{

                override fun onResponse(response: ResponseTVShow?) {
                    responseTVShow.postValue(response)
                }

                override fun onError(anError: ANError?) {
                    responseTVShow.value=ResponseTVShow(anError)
                }
            })
    }
}