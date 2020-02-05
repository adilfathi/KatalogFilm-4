package com.example.katalogfilm_4.ui.movie

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.katalogfilm_4.MovieCatalogue
import com.example.katalogfilm_4.ui.movie.pojo.Response

class MovieFragmentViewModel(application: Application):AndroidViewModel(application) {
    private val responseMovies=MutableLiveData<Response>()

    val movies: MutableLiveData<Response>
    get() {
        return responseMovies
    }
    fun doRequestListMovies(){
        AndroidNetworking.get("https://api.themoviedb.org/3/discover/movie")
            .addQueryParameter("api_key",MovieCatalogue.MOVIE_DB_KEY)
            .addQueryParameter("language",(getApplication()as MovieCatalogue).getLanguage())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsObject(Response::class.java,object : ParsedRequestListener<Response>{
                override fun onResponse(response: Response?) {
                    responseMovies.postValue(response)
                }

                override fun onError(anError: ANError?) {
                    responseMovies.value=Response(anError)
                }

            })
    }
}