package com.example.katalogfilm_4.ui.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.katalogfilm_4.ui.movie.pojo.ResultsItem

@Dao
interface MovieDao {
    companion object {
        const val TABLE_NAME: String = "table_fav_movie"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(resultsItem: ResultsItem)

    @Query("SELECT * FROM ${TABLE_NAME}")
    fun getAllMovie(): MutableList<ResultsItem>

    @Query("DELETE FROM ${TABLE_NAME}")
    fun deleteall()

    @Query("SELECT*FROM ${TABLE_NAME}")
    fun getMovieById(): List<ResultsItem>

    @Query("SELECT*FROM ${TABLE_NAME} WHERE id =:id LIMIT 1 ")
    fun getMovieById(id: Int): ResultsItem?

    @Query("DELETE FROM ${TABLE_NAME} WHERE id =:id")
    fun deleteMovieById(id: Int)
}