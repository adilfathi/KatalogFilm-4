package com.example.katalogfilm_4.ui.movie.pojo

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.katalogfilm_4.ui.movie.MovieAdapter
import com.example.katalogfilm_4.ui.movie.MovieDao

import com.google.gson.annotations.SerializedName

//@Generated("com.robohorse.robopojogenerator")
@Entity(tableName = MovieDao.TABLE_NAME)
class ResultsItem @Ignore constructor(`in`: Parcel) : Parcelable {
    constructor():this(Parcel.obtain())


    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var overview: String?

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    var originalLanguage: String?

    @ColumnInfo(name = "original_tile")
    @SerializedName("original_title")
    var originalTitle: String?

    @ColumnInfo(name = "video")
    @SerializedName("video")
    var isVideo: Boolean

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String?

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var posterPath: String?

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    var backdropPath: String?

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    var releaseDate: String?

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    var popularity: Double

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var voteAverage: Double

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int

    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    var isAdult: Boolean

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    var voteCount: Int

    init {
        overview = `in`.readString()
        originalLanguage = `in`.readString()
        originalTitle = `in`.readString()
        isVideo = `in`.readByte().toInt() != 0
        title = `in`.readString()
        posterPath = `in`.readString()
        backdropPath = `in`.readString()
        releaseDate = `in`.readString()
        popularity = `in`.readDouble()
        voteAverage = `in`.readDouble()
        id = `in`.readInt()
        isAdult = `in`.readByte().toInt() != 0
        voteCount = `in`.readInt()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(overview)
        dest.writeString(originalLanguage)
        dest.writeString(originalTitle)
        dest.writeByte((if (isVideo) 1 else 0).toByte())
        dest.writeString(title)
        dest.writeString(posterPath)
        dest.writeString(backdropPath)
        dest.writeString(releaseDate)
        dest.writeDouble(popularity)
        dest.writeDouble(voteAverage)
        dest.writeInt(id)
        dest.writeByte((if (isAdult) 1 else 0).toByte())
        dest.writeInt(voteCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "ResultsItemTvShow{" +
                "overview = '" + overview + '\''.toString() +
                ",original_language = '" + originalLanguage + '\''.toString() +
                ",original_title = '" + originalTitle + '\''.toString() +
                ",video = '" + isVideo + '\''.toString() +
                ",title = '" + title + '\''.toString() +
                ",poster_path = '" + posterPath + '\''.toString() +
                ",backdrop_path = '" + backdropPath + '\''.toString() +
                ",release_date = '" + releaseDate + '\''.toString() +
                ",popularity = '" + popularity + '\''.toString() +
                ",vote_average = '" + voteAverage + '\''.toString() +
                ",id = '" + id + '\''.toString() +
                ",adult = '" + isAdult + '\''.toString() +
                ",vote_count = '" + voteCount + '\''.toString() +
                "}"
    }



         companion object CREATOR : Parcelable.Creator<ResultsItem> {
             override fun createFromParcel(parcel: Parcel): ResultsItem {
                return ResultsItem(parcel)
             }

             override fun newArray(size : Int): Array<ResultsItem?> {
                 return arrayOfNulls(size)
             }
        }

}