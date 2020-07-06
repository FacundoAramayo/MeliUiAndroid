package com.facundoaramayo.meliuiandroid.modules.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.facundoaramayo.meliuiandroid.databinding.FragmentSearchBinding
import com.facundoaramayo.meliuiandroid.modules.search.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel::class.java)

        return binding.root
    }
}
