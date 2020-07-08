package com.facundoaramayo.meliuiandroid.modules.product

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.facundoaramayo.meliuiandroid.databinding.ProductDetailFragmentBinding
import com.facundoaramayo.meliuiandroid.modules.product.viewmodel.ProductDetailViewModel
import com.facundoaramayo.meliuiandroid.modules.search.SearchFragment

class ProductDetailFragment : Fragment() {

    private lateinit var viewModel: ProductDetailViewModel

    private lateinit var binding: ProductDetailFragmentBinding

    private val args: ProductDetailFragmentArgs by navArgs()

    private var source: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onActivityCreated(savedInstanceState)
        binding = ProductDetailFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProviders.of(this).get(ProductDetailViewModel::class.java)

        source = args.source
        initUI()

        return binding.root
    }

    private fun initUI() {
        setHasOptionsMenu(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    private fun onBackPressed() {
        val action = when (source) {
            SearchFragment.TAG -> ProductDetailFragmentDirections.actionProductDetailFragmentToNavigationSearch()
            else -> ProductDetailFragmentDirections.actionProductDetailFragmentToNavigationHome()
        }
        findNavController().navigate(action)
    }

    companion object {
        const val TAG = "ProductDetail"
    }
}
