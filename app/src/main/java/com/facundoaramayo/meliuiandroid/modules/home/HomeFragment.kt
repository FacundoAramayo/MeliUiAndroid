package com.facundoaramayo.meliuiandroid.modules.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.facundoaramayo.meliuiandroid.databinding.FragmentHomeBinding
import com.facundoaramayo.meliuiandroid.modules.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)

        return binding.root
    }
}
