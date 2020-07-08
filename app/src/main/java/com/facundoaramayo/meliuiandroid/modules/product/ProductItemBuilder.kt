package com.facundoaramayo.meliuiandroid.modules.product

import android.content.res.Resources
import com.facundoaramayo.meliuiandroid.R
import com.facundoaramayo.meliuiandroid.base.EMPTY_STRING
import com.facundoaramayo.meliuiandroid.modules.product.model.ProductModel
import com.facundoaramayo.meliuiandroid.modules.product.model.Shipping

class ProductItemBuilder {

    fun getDescriptionData(product: ProductModel, res: Resources): String {
        return "${getConditionData(product, res)} \n${getShippingData(product.shipping, res)} \n${getAvailabilityData(product, res)}"
    }

    private fun getConditionData(product: ProductModel, resources: Resources): String {
        return when (product.condition) {
            "used" -> resources.getString(R.string.condition_used)
            "new" -> resources.getString(R.string.condition_new)
            else -> EMPTY_STRING
        }
    }

    private fun getShippingData(shipping: Shipping?, resources: Resources): String {
        return if (shipping?.freeShipping == true) resources.getString(R.string.free_shipping) else EMPTY_STRING
    }

    private fun getAvailabilityData(product: ProductModel, resources: Resources): String {
        return resources.getString(R.string.quantity_available, product.availableQuantity.toString())
    }

    fun getFormattedPrice(currency: String?, price: Double?): String {
        return when (currency) {
            "ARS" -> price?.let { "$ ${price.toInt()}" } ?: EMPTY_STRING
            else -> price?.let { "$currency ${price.toInt()}" } ?: EMPTY_STRING
        }

    }

    fun getOriginalPriceData(product: ProductModel): String {
        product.price?.let { price ->
            product.originalPrice?.let { originalPrice ->
                if (price < originalPrice) {
                    return getFormattedPrice(product.currencyId, originalPrice)
                }
            }
        }
        return EMPTY_STRING
    }

    companion object {
        val instance = ProductItemBuilder()
    }

}