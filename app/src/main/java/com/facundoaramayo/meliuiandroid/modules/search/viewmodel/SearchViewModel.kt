package com.facundoaramayo.meliuiandroid.modules.search.viewmodel

import androidx.lifecycle.ViewModel
import com.facundoaramayo.meliuiandroid.modules.product.model.ProductResponseModel
import com.facundoaramayo.meliuiandroid.modules.search.repository.SearchRepository
import retrofit2.Response

class SearchViewModel : ViewModel() {

    val respository: SearchRepository = SearchRepository()

    suspend fun getResults(query: String) : Response<ProductResponseModel> {
        return respository.getResults(query)
    }




}