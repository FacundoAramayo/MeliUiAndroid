package com.facundoaramayo.meliuiandroid.modules.product

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.facundoaramayo.meliuiandroid.R
import com.facundoaramayo.meliuiandroid.modules.product.viewmodel.ProductDetailViewModel

class ProductDetailFragment : Fragment() {

    private lateinit var viewModel: ProductDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this).get(ProductDetailViewModel::class.java)
        val root = inflater.inflate(R.layout.product_detail_fragment, container, false)

        return root
    }
}
