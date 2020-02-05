package com.example.katalogfilm_4.ui.tvshow.pojo


import com.androidnetworking.error.ANError
import com.google.gson.annotations.SerializedName

class ResponseTVShow (val anError: ANError?){

    @SerializedName("page")
    var page: Int = 0

    @SerializedName("total_pages")
    var totalPages: Int = 0

    @SerializedName("results")
    val results: List<ResultsItemTvShow>? = null

    @SerializedName("total_results")
    var totalResults: Int = 0

    override fun toString(): String {
        return "ResponseTVShow{" +
                "page = '" + page + '\''.toString() +
                ",total_pages = '" + totalPages + '\''.toString() +
                ",results = '" + results + '\''.toString() +
                ",total_results = '" + totalResults + '\''.toString() +
                "}"
    }
}