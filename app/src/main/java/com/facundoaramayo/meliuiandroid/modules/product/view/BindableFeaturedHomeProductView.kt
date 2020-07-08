package com.facundoaramayo.meliuiandroid.modules.product.view

import android.content.res.Resources
import android.graphics.Paint
import android.view.View
import com.facundoaramayo.meliuiandroid.R
import com.facundoaramayo.meliuiandroid.baseui.setImageUrl
import com.facundoaramayo.meliuiandroid.databinding.ItemFeaturedProductBinding
import com.facundoaramayo.meliuiandroid.modules.home.viewmodel.HomeViewModel
import com.facundoaramayo.meliuiandroid.modules.product.ProductItemBuilder
import com.facundoaramayo.meliuiandroid.modules.product.model.ProductModel
import com.xwray.groupie.viewbinding.BindableItem

class BindableFeaturedHomeProductView(
    private val product: ProductModel,
    private val listener: ProductClickListener,
    private val resources: Resources) : BindableItem<ItemFeaturedProductBinding>() {

    private val productBuilder = ProductItemBuilder.instance

    override fun getLayout(): Int = R.layout.item_featured_product

    override fun initializeViewBinding(view: View): ItemFeaturedProductBinding {
        return ItemFeaturedProductBinding.bind(view)
    }

    override fun bind(viewBinding: ItemFeaturedProductBinding, position: Int) {
        product.run {
            viewBinding.apply {
                imageView.setImageUrl(thumbnail.orEmpty())
                textViewTitle.text = title.orEmpty()
                textViewShortDesc.text = productBuilder.getDescriptionData(product, resources)
                textViewPrice.text = productBuilder.getFormattedPrice(product.currencyId, product.price)
                textViewOriginalPrice.text = productBuilder.getOriginalPriceData(product)
                textViewOriginalPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                textViewMercadoPago.visibility = if (acceptMercadopago == true) View.VISIBLE else View.GONE

                root.setOnClickListener {
                    listener.onClickProduct(this@run)
                }
            }
        }
    }

    interface ProductClickListener {
        fun onClickProduct(product: ProductModel)
    }

}