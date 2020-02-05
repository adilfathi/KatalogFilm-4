package com.example.katalogfilm_4.ui.tvshow

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.katalogfilm_4.ui.tvshow.pojo.ResultsItemTvShow

@Dao
interface TvShowDao {
    companion object{
        const val TABLE_NAME:String="table_fav_tv_show"
    }
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(resultsItemTvShow: ResultsItemTvShow)

    @Query("SELECT * FROM ${TABLE_NAME}")
    fun getAllTvShow():MutableList<ResultsItemTvShow>

    @Query("DELETE FROM ${TABLE_NAME}")
    fun deleteAll()

    @Query("SELECT * FROM ${TABLE_NAME}")
    fun getTvShowById():List<ResultsItemTvShow>

    @Query("SELECT*FROM ${TABLE_NAME} WHERE id = :id LIMIT 1")
    fun getTvShowById(id:Int):ResultsItemTvShow

    @Query("DELETE FROM ${TABLE_NAME} WHERE id = :id")
    fun deleteTvShowById(id: Int)
}