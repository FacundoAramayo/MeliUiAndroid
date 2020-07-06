package com.facundoaramayo.meliuiandroid.modules.product

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.facundoaramayo.meliuiandroid.databinding.ProductDetailFragmentBinding
import com.facundoaramayo.meliuiandroid.modules.product.viewmodel.ProductDetailViewModel

class ProductDetailFragment : Fragment() {

    private lateinit var viewModel: ProductDetailViewModel

    private lateinit var binding: ProductDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onActivityCreated(savedInstanceState)
        binding = ProductDetailFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProviders.of(this).get(ProductDetailViewModel::class.java)

        return binding.root
    }
}
