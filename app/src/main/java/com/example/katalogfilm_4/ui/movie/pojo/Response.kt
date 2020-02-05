package com.example.katalogfilm_4.ui.movie.pojo


import com.androidnetworking.error.ANError
import com.google.gson.annotations.SerializedName

//@Generated("com.robohorse.robopojogenerator")
class Response(val anError: ANError?) {

    @SerializedName("dates")
    var dates: Dates? = null

    @SerializedName("page")
    var page: Int = 0

    @SerializedName("total_pages")
    var totalPages: Int = 0

    @SerializedName("results")
    var results: List<ResultsItem>? = null

    @SerializedName("total_results")
    var totalResults: Int = 0

    override fun toString(): String {
        return "ResponseTVShow{" +
                "dates = '" + dates + '\''.toString() +
                ",page = '" + page + '\''.toString() +
                ",total_pages = '" + totalPages + '\''.toString() +
                ",results = '" + results + '\''.toString() +
                ",total_results = '" + totalResults + '\''.toString() +
                "}"
    }
}