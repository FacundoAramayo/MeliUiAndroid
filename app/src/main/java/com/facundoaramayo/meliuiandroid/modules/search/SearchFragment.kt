package com.facundoaramayo.meliuiandroid.modules.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facundoaramayo.meliuiandroid.databinding.FragmentSearchBinding
import com.facundoaramayo.meliuiandroid.modules.home.HomeFragmentDirections
import com.facundoaramayo.meliuiandroid.modules.product.model.ProductModel
import com.facundoaramayo.meliuiandroid.modules.product.view.BindableProductResultView
import com.facundoaramayo.meliuiandroid.modules.search.viewmodel.SearchViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchFragment : Fragment(), BindableProductResultView.ProductClickListener {

    private lateinit var searchViewModel: SearchViewModel

    private lateinit var binding: FragmentSearchBinding

    private val args: SearchFragmentArgs by navArgs()

    private val productResultsAdapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel::class.java)

        initUI()

        val searchArgs = args.search
        searchArgs?.let {
            performSearch(it)
        }

        return binding.root
    }

    private fun initUI() {
        initRecyclerView()
        binding.textFieldSearch.editText.apply {
            imeOptions = IME_ACTION_SEARCH
            setOnEditorActionListener { view, actionId, event ->
                when (actionId) {
                    IME_ACTION_SEARCH -> {
                        productResultsAdapter.clear()
                        performSearch(view.text.toString())
                        true
                    }
                    else -> false
                }
            }
        }

    }

    private fun initRecyclerView() {
        val productResultsLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.recyclerViewResults.apply {
            layoutManager = productResultsLayoutManager
            adapter = productResultsAdapter
        }
    }

    private fun performSearch(query: String) {
        binding.textFieldSearch.clearFocus()
        hideKeyboard()
        GlobalScope.launch(Dispatchers.Main) {
            val items = searchViewModel.getResults(query).body()?.results

            items?.let {
                binding.lytResults.visibility = View.VISIBLE
                productResultsAdapter.update(items.map { BindableProductResultView(it, this@SearchFragment, resources)})
            }

            Log.d("LOG-", "items: $items")
        }

    }

    override fun onClickProduct(product: ProductModel) {
        val action = SearchFragmentDirections.actionNavigationSearchToProductDetailFragment()
        action.product = product
        action.source = TAG
        findNavController().navigate(action)
    }

    private fun hideKeyboard() {
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(binding.textFieldSearch.windowToken, 0)
    }

    companion object {
        const val TAG = "Search"
    }
}
