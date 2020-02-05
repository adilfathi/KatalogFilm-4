package com.example.katalogfilm_4.ui.movie.pojo


import com.google.gson.annotations.SerializedName

//@Generated("com.robohorse.robopojogenerator")
class Dates {

    @SerializedName("maximum")
    var maximum: String? = null

    @SerializedName("minimum")
    var minimum: String? = null

    override fun toString(): String {
        return "Dates{" +
                "maximum = '" + maximum + '\''.toString() +
                ",minimum = '" + minimum + '\''.toString() +
                "}"
    }
}