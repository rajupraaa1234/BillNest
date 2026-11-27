package com.mobile.core.data.model

data class ShopDetails(
    val shopName: String,
    val logoUri: String? = null,
    val businessType: String = "Grocery",
    val isSetupComplete: Boolean = false
)
