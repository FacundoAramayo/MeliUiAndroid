package com.facundoaramayo.meliuiandroid.modules.product.view

import android.content.res.Resources
import android.graphics.Paint
import android.view.View
import com.facundoaramayo.meliuiandroid.R
import com.facundoaramayo.meliuiandroid.baseui.setImageUrl
import com.facundoaramayo.meliuiandroid.databinding.ItemFeaturedProductBinding
import com.facundoaramayo.meliuiandroid.modules.home.viewmodel.HomeViewModel
import com.facundoaramayo.meliuiandroid.modules.product.model.ProductModel
import com.xwray.groupie.viewbinding.BindableItem

class BindableFeaturedHomeProductView(
    private val product: ProductModel,
    private val listener: ProductClickListener,
    private val viewModel: HomeViewModel,
    private val resources: Resources) : BindableItem<ItemFeaturedProductBinding>() {

    override fun getLayout(): Int = R.layout.item_featured_product

    override fun initializeViewBinding(view: View): ItemFeaturedProductBinding {
        return ItemFeaturedProductBinding.bind(view)
    }

    override fun bind(viewBinding: ItemFeaturedProductBinding, position: Int) {
        product.run {
            viewBinding.apply {
                imageView.setImageUrl(thumbnail.orEmpty())
                textViewTitle.text = title.orEmpty()
                textViewShortDesc.text = viewModel.getDescriptionData(product, resources)
                textViewPrice.text = viewModel.getFormattedPrice(product.currencyId, product.price)
                textViewOriginalPrice.text = viewModel.getOriginalPriceData(product)
                textViewOriginalPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                textViewMercadoPago.visibility = if (acceptMercadopago == true) View.VISIBLE else View.GONE
            }
        }
    }

    interface ProductClickListener {
        fun onClickProduct(product: ProductModel)
    }

}