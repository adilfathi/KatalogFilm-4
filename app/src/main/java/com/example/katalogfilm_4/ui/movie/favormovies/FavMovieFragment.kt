package com.example.katalogfilm_4.ui.movie.favormovies

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.katalogfilm_4.R
import com.example.katalogfilm_4.databinding.FavMovieFragmentBinding
import com.example.katalogfilm_4.ui.movie.MovieAdapter
import com.example.katalogfilm_4.ui.movie.detailmovie.DetailMovieActivity
import com.example.katalogfilm_4.ui.movie.pojo.ResultsItem

class FavMovieFragment: Fragment() {

    private lateinit var favMovieFragmentBinding: FavMovieFragmentBinding
    private lateinit var movieViewModel: FavMovieViewModel
    private lateinit var alertDialog: AlertDialog

    private val getFavMovie = Observer<List<ResultsItem>>{
        val mAdapter=MovieAdapter(it)
        if (it.size>0){
            favMovieFragmentBinding.tvMessage.visibility=View.GONE
            mAdapter.SetOnItemClickListener(object :MovieAdapter.OnItemClickListener{
                override fun onItemClick(view: View, model: ResultsItem) {
                    val goToDetailMovie = Intent(view.context,DetailMovieActivity::class.java)
                    goToDetailMovie.putExtra(DetailMovieActivity.SELECTED_MOVIE,model)
                    startActivity(goToDetailMovie)
                }

            })
            favMovieFragmentBinding.recyclerView.adapter=mAdapter

        }else{
            favMovieFragmentBinding.recyclerView.adapter=null
            favMovieFragmentBinding.tvMessage.visibility=View.VISIBLE
        }
        favMovieFragmentBinding.progressBar.visibility=View.GONE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        favMovieFragmentBinding=DataBindingUtil.inflate(inflater, R.layout.fav_movie_fragment,container,false)
        movieViewModel=ViewModelProviders.of(this).get(FavMovieViewModel::class.java)
        favMovieFragmentBinding.viewmodel=movieViewModel
        return favMovieFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alertDialog=AlertDialog.Builder(view.context).setTitle(getString(R.string.failure)).setPositiveButton("OK"){dialog,which->}.create()

        val layoutManager=LinearLayoutManager(view.context)
    }

    override fun onResume() {
        super.onResume()
        movieViewModel.fetchFunMovies()
        movieViewModel.movies.observe(this,getFavMovie)
    }
}