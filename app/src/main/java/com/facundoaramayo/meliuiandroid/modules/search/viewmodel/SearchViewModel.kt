package com.facundoaramayo.meliuiandroid.modules.search.viewmodel

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.facundoaramayo.meliuiandroid.modules.product.model.ProductResponseModel
import com.facundoaramayo.meliuiandroid.modules.search.repository.SearchRepository
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val respository: SearchRepository = SearchRepository()

    suspend fun getResults(query: String) : Response<ProductResponseModel> {
        return respository.getResults(query)
    }

    fun openProductById(activity: FragmentActivity?, productId: String?) {
        Log.d("LOG-", "productClicked: $productId")
    }

}