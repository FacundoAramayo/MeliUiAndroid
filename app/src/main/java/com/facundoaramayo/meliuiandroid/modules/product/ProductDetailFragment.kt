package com.facundoaramayo.meliuiandroid.modules.product

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.facundoaramayo.meliuiandroid.base.EMPTY_STRING
import com.facundoaramayo.meliuiandroid.baseui.setImageUrl

import com.facundoaramayo.meliuiandroid.databinding.ProductDetailFragmentBinding
import com.facundoaramayo.meliuiandroid.modules.product.model.ProductModel
import com.facundoaramayo.meliuiandroid.modules.product.viewmodel.ProductDetailViewModel
import com.facundoaramayo.meliuiandroid.modules.search.SearchFragment

class ProductDetailFragment : Fragment() {

    private lateinit var viewModel: ProductDetailViewModel

    private lateinit var binding: ProductDetailFragmentBinding

    private val args: ProductDetailFragmentArgs by navArgs()

    private var source: String? = null

    private var product: ProductModel? = null
    private val productBuilder = ProductItemBuilder.instance

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
        product = args.product
        initUI()

        return binding.root
    }

    private fun initUI() {
        setHasOptionsMenu(true)

        product?.let {
            binding.apply {
                imageView.setImageUrl(it.thumbnail.orEmpty())
                textViewTitle.text = it.title.orEmpty()
                textViewCondition.text = productBuilder.getConditionAndSoldData(it, resources)
                textViewPrice.text = productBuilder.getFormattedPrice(it.currencyId, it.price)
                textViewOriginalPrice.text = productBuilder.getOriginalPriceData(it)
                textViewOriginalPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                val availability = productBuilder.getAvailabilityData(it, resources)
                if (availability == EMPTY_STRING) {
                    textViewAvailability.visibility = View.GONE
                } else {
                    textViewAvailability.text = availability
                }
                val shipping = productBuilder.getShippingData(product?.shipping, resources)
                if (shipping == EMPTY_STRING) {
                    textViewShipping.visibility = View.GONE
                } else {
                    textViewShipping.text = shipping
                }
                textViewAvailability.text = productBuilder.getAvailabilityData(it, resources)
                textViewMercadoPago.visibility = if (it.acceptMercadopago == true) View.VISIBLE else View.GONE

                buttonOpenApp.setOnClickListener {
                    val openURL = Intent(Intent.ACTION_VIEW)
                    openURL.data = Uri.parse(product?.permalink)
                    startActivity(openURL)
                }
            }

        } ?: showError()
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

    private fun showError() {

    }

    companion object {
        const val TAG = "ProductDetail"
    }
}
