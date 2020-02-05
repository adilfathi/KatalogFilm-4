package com.example.katalogfilm_4.ui.movie

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
import com.example.katalogfilm_4.databinding.FragmenMovieBinding
import com.example.katalogfilm_4.ui.movie.detailmovie.DetailMovieActivity
import com.example.katalogfilm_4.ui.movie.pojo.Response
import com.example.katalogfilm_4.ui.movie.pojo.ResultsItem

class MovieFragment : Fragment() {
    lateinit var alertDialog: AlertDialog
    lateinit var binding: FragmenMovieBinding
    lateinit var mViewmodel: MovieFragmentViewModel

    private val getMovies = Observer<Response> { response ->
        if (response != null) {
            if (response.anError == null) {
                val mAdapter = MovieAdapter(response.results!!)

                mAdapter.SetOnItemClickListener(object : MovieAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, model: ResultsItem) {
                        val go = Intent(view.context, DetailMovieActivity::class.java)
                        go.putExtra(DetailMovieActivity.SELECTED_MOVIE, model)
                        startActivity(go)
                    }

                })
                binding.recyclerview.adapter = mAdapter
            } else {
                alertDialog.setMessage(response.anError.message)
                alertDialog.show()
            }
        }
        binding.progressBar.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragmen_movie, container, false)
        mViewmodel = ViewModelProviders.of(this).get(MovieFragmentViewModel::class.java)
        binding.viewmodel = mViewmodel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alertDialog = AlertDialog.Builder(view.context).setTitle(getString(R.string.failure))
            .setPositiveButton("OK") { dialog, which -> }.create()

        val layoutManager = LinearLayoutManager(view.context)
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.setHasFixedSize(true)

    }

    override fun onResume() {
        super.onResume()
        mViewmodel.doRequestListMovies()
        mViewmodel.movies.observe(this, getMovies)
    }
}