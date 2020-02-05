package com.example.katalogfilm_4.ui.tvshow.detailtvshow

import android.os.AsyncTask
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.katalogfilm_4.R
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.katalogfilm_4.database.MovieCatalogueDatabase
import com.example.katalogfilm_4.databinding.ActivityDetailMovieBinding
import com.example.katalogfilm_4.databinding.ActivityDetailTvshowBinding
import com.example.katalogfilm_4.ui.movie.pojo.ResultsItem
import com.example.katalogfilm_4.ui.tvshow.pojo.ResultsItemTvShow
import com.google.android.material.snackbar.Snackbar

class DetailTvShowActivity : AppCompatActivity() {
    private var menuItem : Menu? = null
    private var isFavourite : Boolean = false
    private lateinit var movieCatalogueDatabase: MovieCatalogueDatabase
    private lateinit var tvShowViewModel: ResultsItemTvShow
    private lateinit var binding: ActivityDetailTvshowBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        movieCatalogueDatabase = MovieCatalogueDatabase.getDatabase(this)
        val viewModel = ViewModelProviders.of(this).get(DetailTvShowViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_tvshow)

        tvShowViewModel = intent.getParcelableExtra(SELECTED_TV_SHOWS)
        viewModel.resultsItem = tvShowViewModel
        binding.viewmodel=viewModel

        Glide.with(this).load("https://image.tmdb.org/t/p/w185/" + tvShowViewModel.posterPath!!).into(binding.imgPoster)

        title=viewModel.resultsItem!!.name

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        checkFavourite()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
    companion object{
        val SELECTED_TV_SHOWS="selected_tv_shows"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail,menu)
        menuItem=menu
        setFavTvShowDb()
        return true
    }
    private fun setFavTvShowDb(){
        if (isFavourite)
            menuItem?.findItem(R.id.actionfavorite)?.icon=ContextCompat.getDrawable(this,R.drawable.ic_favorite_black_24dp)
        else
            menuItem?.findItem(R.id.actionfavorite)?.icon=ContextCompat.getDrawable(this,R.drawable.ic_favorite_border_black_24dp)
    }
    private fun checkFavourite(){
        AsyncTask.execute {
            val tvShowDb = movieCatalogueDatabase.tvShowDao().getTvShowById(tvShowViewModel.id)

            isFavourite = tvShowDb?.name!=null

            runOnUiThread {
                setFavTvShowDb()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.actionfavorite ->{
                if (isFavourite){
                    AsyncTask.execute {
                        movieCatalogueDatabase.tvShowDao().deleteTvShowById(tvShowViewModel.id)
                        runOnUiThread {
                            isFavourite=!isFavourite
                            setFavTvShowDb()
                            Snackbar.make(binding.root,getString(R.string.favorite_success_remove_movie),Snackbar.LENGTH_LONG).show()
                        }
                    }
                }else{
                    AsyncTask.execute{
                        movieCatalogueDatabase.tvShowDao().insert(tvShowViewModel)
                        runOnUiThread {
                            isFavourite=!isFavourite
                            setFavTvShowDb()
                            Snackbar.make(binding.root,getString(R.string.favorite_success_add_movie),Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}