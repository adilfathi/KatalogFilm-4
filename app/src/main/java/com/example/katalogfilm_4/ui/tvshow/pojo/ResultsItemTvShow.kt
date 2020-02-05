package com.example.katalogfilm_4.ui.tvshow.pojo

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import  androidx.room.PrimaryKey
import com.example.katalogfilm_4.ui.tvshow.TvShowDao
import com.google.gson.annotations.SerializedName

@Entity(tableName = TvShowDao.TABLE_NAME)
class ResultsItemTvShow @Ignore constructor(`in`:Parcel):Parcelable {
    constructor():this(Parcel.obtain())

    @ColumnInfo(name = "first_air_date")
    @SerializedName("first_air_date")
    var firstAirDate: String?

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var overview: String?

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    var originalLanguage: String?

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var posterPath: String?

    @Ignore
    @ColumnInfo(name = "origin_country")
    @SerializedName("origin_country")
    var originCountry: List<String>?


    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    var backdropPath: String?

    @ColumnInfo(name = "original_name")
    @SerializedName("original_name")
    var originalName: String?

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    var popularity: Double

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var voteAverage: Double

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String?

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    var voteCount: Int

    override fun toString(): String {
        return "ResultsItemTvShow{" +
                "first_air_date = '" + firstAirDate + '\''.toString() +
                ",overview = '" + overview + '\''.toString() +
                ",original_language = '" + originalLanguage + '\''.toString() +
                ",poster_path = '" + posterPath + '\''.toString() +
                ",origin_country = '" + originCountry + '\''.toString() +
                ",backdrop_path = '" + backdropPath + '\''.toString() +
                ",original_name = '" + originalName + '\''.toString() +
                ",popularity = '" + popularity + '\''.toString() +
                ",vote_average = '" + voteAverage + '\''.toString() +
                ",name = '" + name + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                ",vote_count = '" + voteCount + '\''.toString() +
                "}"
    }

    init {
        this.firstAirDate = `in`.readString()
        this.overview = `in`.readString()
        this.originalLanguage = `in`.readString()
        this.posterPath = `in`.readString()
        this.originCountry = `in`.createStringArrayList()
        this.backdropPath = `in`.readString()
        this.originalName = `in`.readString()
        this.popularity = `in`.readDouble()
        this.voteAverage = `in`.readDouble()
        this.name = `in`.readString()
        this.id = `in`.readInt()
        this.voteCount = `in`.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstAirDate)
        parcel.writeString(overview)
        parcel.writeString(originalLanguage)
        parcel.writeString(posterPath)
        parcel.writeStringList(originCountry)
        parcel.writeString(backdropPath)
        parcel.writeString(originalName)
        parcel.writeDouble(popularity)
        parcel.writeDouble(voteAverage)
        parcel.writeString(name)
        parcel.writeInt(id)
        parcel.writeInt(voteCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResultsItemTvShow> {
        override fun createFromParcel(parcel: Parcel): ResultsItemTvShow {
            return ResultsItemTvShow(parcel)
        }

        override fun newArray(size: Int): Array<ResultsItemTvShow?> {
            return arrayOfNulls(size)
        }
    }
}