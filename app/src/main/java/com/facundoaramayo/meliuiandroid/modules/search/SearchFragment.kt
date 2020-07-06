package com.facundoaramayo.meliuiandroid.modules.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.facundoaramayo.meliuiandroid.databinding.FragmentSearchBinding
import com.facundoaramayo.meliuiandroid.modules.search.viewmodel.SearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

        initUI()

        return binding.root
    }

    private fun initUI() {
        binding.textFieldSearch.editText.apply {
            imeOptions = IME_ACTION_SEARCH
            setOnEditorActionListener { view, actionId, event ->
                when (actionId) {
                    IME_ACTION_SEARCH -> {
                        performSearch(view.text.toString())
                        true
                    }
                    else -> false
                }

            }
        }

    }

    private fun performSearch(query: String) {

        GlobalScope.launch(Dispatchers.Main) {
            val items = searchViewModel.getResults(query).body()?.results
            Log.d("LOG-", "items: $items")
        }

    }
}
