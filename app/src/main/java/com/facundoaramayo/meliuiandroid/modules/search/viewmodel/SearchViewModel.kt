package com.facundoaramayo.meliuiandroid.modules.search.viewmodel

import android.content.res.Resources
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.facundoaramayo.meliuiandroid.R
import com.facundoaramayo.meliuiandroid.base.EMPTY_STRING
import com.facundoaramayo.meliuiandroid.modules.product.model.ProductModel
import com.facundoaramayo.meliuiandroid.modules.product.model.ProductResponseModel
import com.facundoaramayo.meliuiandroid.modules.product.model.Shipping
import com.facundoaramayo.meliuiandroid.modules.search.repository.SearchRepository
import retrofit2.Response

class SearchViewModel : ViewModel() {

    val respository: SearchRepository = SearchRepository()

    suspend fun getResults(query: String) : Response<ProductResponseModel> {
        return respository.getResults(query)
    }

    fun openProductById(activity: FragmentActivity?, productId: String?) {
        Log.d("LOG-", "productClicked: $productId")
    }

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


}