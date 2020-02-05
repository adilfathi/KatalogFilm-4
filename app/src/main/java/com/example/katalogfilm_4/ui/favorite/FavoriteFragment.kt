package com.example.katalogfilm_4.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.katalogfilm_4.R
import com.example.katalogfilm_4.databinding.FragmenFavouriteBinding

class FavoriteFragment : Fragment() {
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var binding: FragmenFavouriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragmen_favourite, container, false)
        favoriteViewModel=ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        binding.viewmodel=favoriteViewModel
        binding.viewPager.adapter=SectionPagerAdapter(binding.root.context,childFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewPager)
        return binding.root
    }
}