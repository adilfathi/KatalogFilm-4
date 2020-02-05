package com.example.katalogfilm_4.ui.tvshow.favtvshow

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
import com.example.katalogfilm_4.databinding.FavTvshowFragmenyBinding
import com.example.katalogfilm_4.ui.tvshow.TvShowAdapter
import com.example.katalogfilm_4.ui.tvshow.detailtvshow.DetailTvShowActivity
import com.example.katalogfilm_4.ui.tvshow.pojo.ResultsItemTvShow


class FavTVShowFragment: Fragment() {
    private lateinit var favTvshowFragmenyBinding: FavTvshowFragmenyBinding
    private lateinit var mViewModel: FavTVShowViewModel
    private lateinit var alertDialog: AlertDialog

    val getTvShow = Observer<List<ResultsItemTvShow>>{
        val mAdapter = TvShowAdapter(it)
        if (it.size>0){
            favTvshowFragmenyBinding.tvMessage.visibility=View.GONE
            mAdapter.SetOnItemClickListener(object : TvShowAdapter.OnItemClickListener{
                override fun onItemClick(view: View, model: ResultsItemTvShow) {
                    val goToDetailMovie = Intent(view.context,DetailTvShowActivity::class.java)
                    goToDetailMovie.putExtra(DetailTvShowActivity.SELECTED_TV_SHOWS,model)
                    startActivity(goToDetailMovie)
                }
            })
            favTvshowFragmenyBinding.recyclerView.adapter=mAdapter
        }else{
            favTvshowFragmenyBinding.recyclerView.adapter=null
            favTvshowFragmenyBinding.tvMessage.visibility=View.GONE
        }
        favTvshowFragmenyBinding.progressBar.visibility=View.GONE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        favTvshowFragmenyBinding=DataBindingUtil.inflate(inflater,R.layout.fav_tvshow_fragmeny,container,false)
        mViewModel=ViewModelProviders.of(this).get(FavTVShowViewModel::class.java)
        favTvshowFragmenyBinding.viewmodel=mViewModel
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alertDialog=AlertDialog.Builder(view.context).setTitle(getString(R.string.failure)).setPositiveButton("OK"){dialog,which ->}.create()

        val layoutManager = LinearLayoutManager(view.context)
        favTvshowFragmenyBinding.recyclerView.layoutManager=layoutManager
        favTvshowFragmenyBinding.recyclerView.setHasFixedSize(true)
        favTvshowFragmenyBinding.viewmodel=mViewModel
    }

    override fun onResume() {
        super.onResume()
        mViewModel.fetchFavTvShow()
        mViewModel.getTvShowList.observe(this,getTvShow)
    }


}