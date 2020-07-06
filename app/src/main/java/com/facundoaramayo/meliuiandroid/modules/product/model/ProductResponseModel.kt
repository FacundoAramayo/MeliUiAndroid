package com.facundoaramayo.meliuiandroid.modules.product.model

import com.google.gson.annotations.SerializedName

data class ProductResponseModel(
    @SerializedName("results")
    val results: List<ProductModel>
)