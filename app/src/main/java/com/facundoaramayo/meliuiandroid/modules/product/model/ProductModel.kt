package com.facundoaramayo.meliuiandroid.modules.product.model

import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("price") val price: Double?,
    @SerializedName("currency_id") val currencyId: String?,
    @SerializedName("available_quantity") val availableQuantity: Int?,
    @SerializedName("condition") val condition: String?,
    @SerializedName("shipping") val shipping: Shipping?,
    @SerializedName("permalink") val permalink: String?,
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("accepts_mercadopago") val acceptMercadopago: Boolean?,
    @SerializedName("original_price") val originalPrice: Double?
)

data class Shipping(
    @SerializedName("free_shipping") val freeShipping: Boolean?,
    @SerializedName("mode") val mode: String?,
    @SerializedName("logistic_type") val logisticType: String?,
    @SerializedName("store_pick_up") val storePickUp: String?
)