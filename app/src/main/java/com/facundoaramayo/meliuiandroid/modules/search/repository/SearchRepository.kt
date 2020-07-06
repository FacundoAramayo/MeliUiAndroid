package com.facundoaramayo.meliuiandroid.modules.search.repository

import com.facundoaramayo.meliuiandroid.base.SITE_ID
import com.facundoaramayo.meliuiandroid.base.rest.RetrofitService
import com.facundoaramayo.meliuiandroid.modules.search.services.SearchAPI

class SearchRepository {

    private var searchAPI: SearchAPI = RetrofitService.createService(SearchAPI::class.java)

    suspend fun getResults(query: String) = searchAPI.getResults(SITE_ID, query)

}