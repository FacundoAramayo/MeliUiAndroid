package com.facundoaramayo.meliuiandroid.modules.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facundoaramayo.meliuiandroid.base.EMPTY_STRING
import com.facundoaramayo.meliuiandroid.base.QuickSearch
import com.facundoaramayo.meliuiandroid.databinding.FragmentHomeBinding
import com.facundoaramayo.meliuiandroid.modules.home.viewmodel.HomeViewModel
import com.facundoaramayo.meliuiandroid.modules.product.model.ProductModel
import com.facundoaramayo.meliuiandroid.modules.product.view.BindableFeaturedHomeProductView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), BindableFeaturedHomeProductView.ProductClickListener {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding

    private val freeShippingProductsAdapter by lazy { GroupAdapter<GroupieViewHolder>() }

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
        initRecyclerView()
        binding.textFieldSearch.editText.apply {
            imeOptions = EditorInfo.IME_ACTION_SEARCH
            setOnEditorActionListener { view, actionId, event ->
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        val action = HomeFragmentDirections.actionNavigationHomeToNavigationSearch()
                        action.search = view.text.toString()
                        if (view.text.toString() != EMPTY_STRING) {
                            hideKeyboard()
                            findNavController().navigate(action)
                        }
                        true
                    }
                    else -> false
                }
            }

            binding.apply {
                buttonRecommendationShoes.text = QuickSearch.SHOES.value
                buttonRecommendationShoes.setOnClickListener {
                    showFreeShippingProducts(QuickSearch.SHOES.value)
                }

                buttonRecommendationLaptops.text = QuickSearch.LAPTOPS.value
                buttonRecommendationLaptops.setOnClickListener {
                    showFreeShippingProducts(QuickSearch.LAPTOPS.value)
                }

                buttonRecommendationFreezer.text = QuickSearch.FREEZER.value
                buttonRecommendationFreezer.setOnClickListener {
                    showFreeShippingProducts(QuickSearch.FREEZER.value)
                }

                buttonRecommendationKitchen.text = QuickSearch.KITCHEN.value
                buttonRecommendationKitchen.setOnClickListener {
                    showFreeShippingProducts(QuickSearch.KITCHEN.value)
                }

                buttonRecommendationMotorcycle.text = QuickSearch.MOTORCYCLE.value
                buttonRecommendationMotorcycle.setOnClickListener {
                    showFreeShippingProducts(QuickSearch.MOTORCYCLE.value)
                }

                buttonRecommendationApartment.text = QuickSearch.APARTMENT.value
                buttonRecommendationApartment.setOnClickListener {
                    showFreeShippingProducts(QuickSearch.APARTMENT.value)
                }
            }
        }
        showFreeShippingProducts(QuickSearch.SHOES.value)
    }

    private fun initRecyclerView() {
        val productResultsLayoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        binding.recyclerViewFreeShipping.apply {
            layoutManager = productResultsLayoutManager
            adapter = freeShippingProductsAdapter
        }
    }

    private fun showFreeShippingProducts(query: String) {

        GlobalScope.launch(Dispatchers.Main) {
            val items = homeViewModel.getFreeShippingProduct(query).body()?.results

            items?.let {
                binding.lytFreeShipping.visibility = View.VISIBLE
                freeShippingProductsAdapter.update(items.map { BindableFeaturedHomeProductView(it, this@HomeFragment, resources) })
            }
        }

    }

    private fun hideKeyboard() {
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(binding.textFieldSearch.windowToken, 0)
    }

    override fun onClickProduct(product: ProductModel) {
        val action = HomeFragmentDirections.actionNavigationHomeToProductDetailFragment()
        action.product = product
        action.source = TAG
        findNavController().navigate(action)
    }

    companion object {
        const val TAG = "Home"
    }

}
