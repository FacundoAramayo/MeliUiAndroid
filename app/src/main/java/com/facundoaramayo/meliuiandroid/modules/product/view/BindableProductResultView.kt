package com.facundoaramayo.meliuiandroid.modules.product.view

import android.content.res.Resources
import android.graphics.Paint
import android.view.View
import com.facundoaramayo.meliuiandroid.R
import com.facundoaramayo.meliuiandroid.baseui.setImageUrl
import com.facundoaramayo.meliuiandroid.databinding.ItemResultProductBinding
import com.facundoaramayo.meliuiandroid.modules.product.ProductItemBuilder
import com.facundoaramayo.meliuiandroid.modules.product.model.ProductModel
import com.facundoaramayo.meliuiandroid.modules.search.viewmodel.SearchViewModel
import com.xwray.groupie.viewbinding.BindableItem

class BindableProductResultView(
    private val product: ProductModel,
    private val listener: ProductClickListener,
    private val resources: Resources) : BindableItem<ItemResultProductBinding>() {

    private val productBuilder = ProductItemBuilder.instance

    override fun getLayout(): Int = R.layout.item_result_product

    override fun initializeViewBinding(view: View): ItemResultProductBinding {
        return ItemResultProductBinding.bind(view)
    }

    override fun bind(viewBinding: ItemResultProductBinding, position: Int) {
        product.run {
            viewBinding.apply {
                imageView.setImageUrl(thumbnail.orEmpty())
                textViewTitle.text = title.orEmpty()
                textViewCondition.text = productBuilder.getConditionAndSoldData(product, resources)
                textViewPrice.text = productBuilder.getFormattedPrice(product.currencyId, product.price)
                textViewOriginalPrice.text = productBuilder.getOriginalPriceData(product)
                textViewOriginalPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

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