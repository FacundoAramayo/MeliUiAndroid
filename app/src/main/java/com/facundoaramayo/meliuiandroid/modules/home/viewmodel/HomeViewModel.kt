package com.facundoaramayo.meliuiandroid.modules.home.viewmodel

import androidx.lifecycle.ViewModel
import com.facundoaramayo.meliuiandroid.modules.product.model.ProductResponseModel
import com.facundoaramayo.meliuiandroid.modules.search.repository.SearchRepository
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val respository: SearchRepository = SearchRepository()

    suspend fun getFreeShippingProduct(query: String): Response<ProductResponseModel> {
        return respository.getResults(query)
    }

}