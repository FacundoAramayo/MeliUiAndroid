package com.facundoaramayo.meliuiandroid.modules.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.facundoaramayo.meliuiandroid.base.EMPTY_STRING
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

        initUI()

        return binding.root
    }

    private fun initUI() {

        binding.textFieldSearch.editText.apply {
            imeOptions = EditorInfo.IME_ACTION_SEARCH
            setOnEditorActionListener { view, actionId, event ->
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        val action = HomeFragmentDirections.actionNavigationHomeToNavigationSearch()
                        action.search = view.text.toString()
                        if (view.text.toString() != EMPTY_STRING) {
                            findNavController().navigate(action)
                        }
                        true
                    }
                    else -> false
                }
            }
        }
    }
}
