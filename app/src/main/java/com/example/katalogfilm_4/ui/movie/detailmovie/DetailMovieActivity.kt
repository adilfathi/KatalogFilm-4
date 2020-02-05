package com.example.katalogfilm_4.ui.movie.detailmovie

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.katalogfilm_4.R
import com.example.katalogfilm_4.database.MovieCatalogueDatabase
import com.example.katalogfilm_4.databinding.ActivityDetailMovieBinding
import com.example.katalogfilm_4.databinding.ActivityDetailTvshowBinding
import com.example.katalogfilm_4.ui.movie.pojo.ResultsItem
import com.google.android.material.snackbar.Snackbar

class DetailMovieActivity : AppCompatActivity() {

    private var menuItem : Menu?=null
    private var isFavorit : Boolean=false
    private lateinit var movieCatalogueDatabase: MovieCatalogueDatabase
    private lateinit var moviemodel : ResultsItem
    lateinit var binding : ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieCatalogueDatabase= MovieCatalogueDatabase.getDatabase(this)
        val viewModel=ViewModelProviders.of(this).get(DetailMovieViewModel::class.java)

        binding=DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)

        moviemodel=intent.getParcelableExtra(SELECTED_MOVIE)
        viewModel.resultsItem=moviemodel
        binding.setViewmodel(viewModel)

        Glide.with(this).load("https://image.tmdb.org/t/p/w185/" +moviemodel.posterPath!!).into(binding.imgPoster)

        setTitle(viewModel.resultsItem!!.title)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        checkFavorite()


    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object{
        val SELECTED_MOVIE="selected_movie"
    }

    private fun setFavMovieDb(){
        if (isFavorit)
            menuItem?.findItem(R.id.actionfavorite)?.icon=ContextCompat.getDrawable(this,R.drawable.ic_favorite_black_24dp)
        else
            menuItem?.findItem(R.id.actionfavorite)?.icon=ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail,menu)
        menuItem=menu
        setFavMovieDb()
        return true
    }
    private fun checkFavorite(){
        AsyncTask.execute{
            val moviedb =movieCatalogueDatabase.movieDao().getMovieById(moviemodel.id)

            isFavorit=moviedb?.title!=null

            runOnUiThread {
                setFavMovieDb()

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.actionfavorite->{
                if (isFavorit){
                    AsyncTask.execute {
                        movieCatalogueDatabase.movieDao().deleteMovieById(moviemodel.id)
                        runOnUiThread {
                            isFavorit=!isFavorit
                            setFavMovieDb()
                            Snackbar.make(binding.root,getString(R.string.favorite_success_add_movie),Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}