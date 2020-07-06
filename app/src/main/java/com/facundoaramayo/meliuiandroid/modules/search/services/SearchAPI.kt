package com.facundoaramayo.meliuiandroid.modules.search.services

import com.facundoaramayo.meliuiandroid.base.AGENT
import com.facundoaramayo.meliuiandroid.base.CACHE
import com.facundoaramayo.meliuiandroid.modules.product.model.ProductResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchAPI {

    @Headers(CACHE, AGENT)
    @GET("sites/{site_id}/search")
    suspend fun getResults(
        @Path(value = "site_id") siteId: String,
        @Query("q") query: String
    ) : Response<ProductResponseModel>

}