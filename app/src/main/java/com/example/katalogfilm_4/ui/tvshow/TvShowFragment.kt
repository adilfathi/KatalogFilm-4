package com.example.katalogfilm_4.ui.tvshow

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
import com.example.katalogfilm_4.databinding.FragmenTvshowBinding
import com.example.katalogfilm_4.ui.movie.detailmovie.DetailMovieActivity
import com.example.katalogfilm_4.ui.tvshow.detailtvshow.DetailTvShowActivity
import com.example.katalogfilm_4.ui.tvshow.pojo.ResponseTVShow
import com.example.katalogfilm_4.ui.tvshow.pojo.ResultsItemTvShow

class TvShowFragment : Fragment() {
    lateinit var alertDialog: AlertDialog
    lateinit var binding: FragmenTvshowBinding
    lateinit var mViewModel: TvShowViewModel

    val getTvShow = Observer<ResponseTVShow> { responseTVShows ->
        if (responseTVShows != null) {
            if (responseTVShows.anError == null) {
                val mAdapter = TvShowAdapter(responseTVShows.results!!)

                mAdapter.SetOnItemClickListener(object : TvShowAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, model: ResultsItemTvShow) {
                        val goToDetailMovie = Intent(view.context, DetailTvShowActivity::class.java)
                        goToDetailMovie.putExtra(DetailTvShowActivity.SELECTED_TV_SHOWS, model)
                        startActivity(goToDetailMovie)
                    }
                })
                binding.recyclerView.adapter = mAdapter
            } else {
                alertDialog.setMessage(responseTVShows.anError.message)
                alertDialog.show()
            }
        }
        binding.progressBar.visibility=View.GONE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragmen_tvshow,container,false)
        mViewModel=ViewModelProviders.of(this).get(TvShowViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alertDialog=AlertDialog.Builder(view.context).setTitle(getString(R.string.failure)).setPositiveButton("OK"){ dialog,which -> }.create()

        val layoutManager = LinearLayoutManager(view.context)
        binding.recyclerView.layoutManager=layoutManager
        binding.recyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        mViewModel.doRequestListTvShows()
        mViewModel.getTvShowList.observe(this,getTvShow)
    }
}